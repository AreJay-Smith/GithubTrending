package com.example.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class ProjectsDataStoreFactoryTest {

    private val cacheStore = mock<ProjectsCachedDataStore>()
    private val remoteStore = mock<ProjectsRemoteDataStore>()
    private val factory = ProjectsDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun getDataStoreReturnsRemotesStoreWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataSore(true, true))
    }

    @Test
    fun getDataStoreReturnsRemotesStoreWhenProjectsNotCached() {
        assertEquals(remoteStore, factory.getDataSore(false, false))
    }

    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getDataSore(true, false))
    }

    @Test
    fun getCachedDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}