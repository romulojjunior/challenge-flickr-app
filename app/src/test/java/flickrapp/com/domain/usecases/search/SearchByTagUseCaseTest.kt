package flickrapp.com.domain.usecases.search

import flickrapp.com.domain.models.SearchResult
import flickrapp.com.domain.repository.SearchRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class SearchByTagUseCaseTest {

    @MockK
    lateinit var repository: SearchRepository

    @MockK
    lateinit var searchResult: SearchResult

    private lateinit var useCase: SearchByTagUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = SearchByTagUseCase(repository)
    }

    @Test
     fun executeWithSuccess() {
        runBlocking {
            val tag = "TagTest"
            coEvery { repository.searchByTags(tag) } returns searchResult
            val result = useCase.execute(tag)

            coVerify { repository.searchByTags(tag) }
            assertEquals(searchResult, result)
        }
    }

    @Test
    fun executeWithError() {
        assertThrows(Exception::class.java) {
            runBlocking {
                val tag = ""
                useCase.execute(tag)
            }
        }
    }
}