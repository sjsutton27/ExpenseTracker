package com.example.expensetracker.data.repository

import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.expense.ExpenseItem
import com.example.expensetracker.data.remote.api.LogoApi
import com.example.expensetracker.data.remote.responses.ExpenseImage
import com.example.expensetracker.data.util.prepareExpense
import com.example.expensetracker.domain.repository.ExpenseRepository
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

class ExpenseRepositoryImpl(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val logoApi: LogoApi
) : ExpenseRepository {

    private val userId: String?
        get() = auth.currentUser?.uid

    private fun getExpenseRef(): DatabaseReference? {
        val id = userId ?: return null
        return database.reference
            .child("Users")
            .child(id)
    }

    override fun addExpense(
        expense: ExpenseItem
    ): Flow<Resource<ExpenseItem>> = flow {
        emit(value = Resource.Loading())
        try {
            val preparedExpense = prepareExpense(this@ExpenseRepositoryImpl, expense)
            val ref = getExpenseRef()
                ?: error("User not logged in")
            val expenseId = ref.push().key
                ?: error("Failed to generate expense id.")
            val newExpense = preparedExpense.copy(
                id = expenseId
            )
            ref
                .child(expenseId)
                .setValue(newExpense)
                .await()
            emit(value = Resource.Success(data = newExpense))
        } catch (e: Exception) {
            emit(value = Resource.Error(message = e.message ?: "Failed to add expense"))
        }
    }

    override fun updateExpense(
        expense: ExpenseItem
    ): Flow<Resource<ExpenseItem>> = flow {
        emit(value = Resource.Loading())
        try {
            val preparedExpense = prepareExpense(this@ExpenseRepositoryImpl, expense)
            val ref = getExpenseRef()
                ?: error("User not logged in")
            ref
                .child(preparedExpense.id)
                .setValue(preparedExpense)
                .await()
            emit(value = Resource.Success(data = preparedExpense))
        } catch (e: Exception) {
            emit(value = Resource.Error(message = e.message ?: "Failed to update expense"))
        }
    }

    override fun getExpenses(): Flow<Resource<List<ExpenseItem>>> =
        callbackFlow {
            trySend(Resource.Loading())
            val ref = getExpenseRef()
            if (ref == null) {
                close()
                return@callbackFlow
            }
            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val expenses = snapshot.children.mapNotNull { child ->
                        child.getValue(ExpenseItem::class.java)
                    }
                    trySend(element = Resource.Success(data = expenses)).isSuccess
                }
                override fun onCancelled(error: DatabaseError) {
                    if (error.code == DatabaseError.PERMISSION_DENIED && auth.currentUser == null) {
                        close()
                    } else {
                        trySend(Resource.Error(error.message))
                    }
                }
            }
            ref.addValueEventListener(listener)
            awaitClose {
                ref.removeEventListener(listener)
            }
        }

    override fun deleteExpense(
        id: String
    ): Flow<Resource<Unit>> = flow {
        emit(value = Resource.Loading())
        try {
            val ref = getExpenseRef()
                ?: error("User not logged in")
            ref
                .child(id)
                .removeValue()
                .await()
            emit(value = Resource.Success(Unit))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.message ?: "Failed to delete expense"
                )
            )
        }
    }

    @Suppress("SwallowedException")
    override suspend fun getMerchantLogo(
        merchant: String
    ): ExpenseImage? {
        return try {
            logoApi
                .searchLogos(merchant)
        } catch (e: Exception) {
            null
        }
    }
}
