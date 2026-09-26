package com.example.expensetracker.data.repository

import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.income.IncomeItem
import com.example.expensetracker.domain.repository.IncomeRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class IncomeRepositoryImpl(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) : IncomeRepository {

    private val userId: String?
        get() = auth.currentUser?.uid

    private fun getIncomeRef(): DatabaseReference? {
        val id = userId ?: return null

        return database.reference
            .child("income")
            .child(id)
    }

    override fun addIncome(
        income: IncomeItem
    ): Flow<Resource<IncomeItem>> = flow {
        emit(Resource.Loading())

        try {
            val ref = getIncomeRef()
                ?: error("User not logged in")

            val incomeId = ref.push().key
                ?: error("Failed to generate income id.")

            val newIncome = income.copy(
                id = incomeId
            )

            ref
                .child(incomeId)
                .setValue(newIncome)
                .await()

            emit(Resource.Success(data = newIncome))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.message ?: "Failed to add income"
                )
            )
        }
    }

    override fun updateIncome(
        income: IncomeItem
    ): Flow<Resource<IncomeItem>> = flow {
        emit(Resource.Loading())

        try {
            val ref = getIncomeRef()
                ?: error("User not logged in")

            ref
                .child(income.id)
                .setValue(income)
                .await()

            emit(Resource.Success(data = income))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.message ?: "Failed to update income"
                )
            )
        }
    }

    override fun getIncome(): Flow<Resource<List<IncomeItem>>> =
        callbackFlow {
            trySend(Resource.Loading())

            val ref = getIncomeRef()

            if (ref == null) {
                close()
                return@callbackFlow
            }

            val listener = object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val income = snapshot.children.mapNotNull { child ->
                        child.getValue(IncomeItem::class.java)
                    }

                    trySend(
                        Resource.Success(data = income)
                    )
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(
                        Resource.Error(
                            message = error.message
                        )
                    )
                }
            }

            ref.addValueEventListener(listener)

            awaitClose {
                ref.removeEventListener(listener)
            }
        }

    override fun deleteIncome(
        id: String
    ): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())

        try {
            val ref = getIncomeRef()
                ?: error("User not logged in")

            ref
                .child(id)
                .removeValue()
                .await()

            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.message ?: "Failed to delete income"
                )
            )
        }
    }
}
