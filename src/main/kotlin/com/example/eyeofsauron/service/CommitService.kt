package com.example.eyeofsauron.service

import com.example.eyeofsauron.entity.Commit
import com.example.eyeofsauron.repository.CommitRepository
import org.springframework.stereotype.Service

/**
 * Сервис для работы с событиями считывания меток устройствами
 * @author rodkinsi
 */
@Service
class CommitService(private val repository: CommitRepository) {
    fun getAllCommits(): List<Commit> = repository.findAll()

    fun getCommitById(id: Long) = repository.findById(id)

    fun createCommit(commit: Commit) = repository.save(commit)

    fun updateCommit(commit: Commit) = repository.save(commit)

    fun delete(commit: Commit) = repository.delete(commit)

    fun deleteCommitById(id: Long) = repository.deleteById(id)
}