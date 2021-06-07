package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.Commit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CommitRepository : JpaRepository<Commit, Long>, JpaSpecificationExecutor<Commit>