package com.example.eyeofsauron.repository

import com.example.eyeofsauron.entity.Commit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CommitRepository : JpaRepository<Commit, Long>, JpaSpecificationExecutor<Commit>