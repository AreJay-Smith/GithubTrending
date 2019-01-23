package com.example.data.test.factory

import com.example.data.model.ProjectEntity
import com.example.domain.model.Project

object ProjectFactory {

    fun makeProjectEntity() = ProjectEntity( DataFactory.randomString(),
        DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(),
        DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(),
        DataFactory.randomBoolean())

    fun makeProject() = Project( DataFactory.randomString(),
        DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(),
        DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(),
        DataFactory.randomBoolean())
}