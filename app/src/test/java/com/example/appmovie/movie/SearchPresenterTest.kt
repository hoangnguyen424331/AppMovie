package com.example.appmovie.movie

import com.example.appmovie.data.model.SearchMovie
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.data.source.repository.MovieRepository
import com.example.appmovie.ui.search.SearchMovieContact
import com.example.appmovie.ui.search.SearchMoviePresenter
import com.example.appmovie.utils.FakeData
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doAnswer
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class SearchPresenterTest {

    @Mock
    private lateinit var searchMoviePresenter: SearchMoviePresenter
    private lateinit var listSearchMovie: MutableList<SearchMovie?>

    @Mock
    private lateinit var view: SearchMovieContact.View

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var exception: Exception

    @Before
    fun setUp() {
        listSearchMovie = FakeData.SEARCH_MODEL
        searchMoviePresenter = SearchMoviePresenter(movieRepository)
        searchMoviePresenter.onView(view)
    }

    @Test
    fun `get data search result success`() {
        `when`(
            movieRepository.getSearchMovie(
                anyInt(),
                anyString(),
                org.mockito.kotlin.any()
            )
        ).doAnswer {
            (it.arguments[2] as OnFetchDataJsonListener<MutableList<SearchMovie?>>).onSuccess(
                listSearchMovie
            )
        }
        searchMoviePresenter.getDataSearch(FakeData.PAGE_SEARCH, FakeData.QUERY_SEARCH)
        verify(view).onSearchSuccess(listSearchMovie)
    }

    @Test
    fun `get data search result error`() {
        `when`(
            movieRepository.getSearchMovie(
                anyInt(),
                anyString(),
                org.mockito.kotlin.any()
            )
        ).doAnswer {
            (it.arguments[2] as OnFetchDataJsonListener<MutableList<SearchMovie?>>).onError(
                exception
            )
        }
        searchMoviePresenter.getDataSearch(FakeData.PAGE_SEARCH, FakeData.QUERY_SEARCH)
        verify(view).onError(exception)
    }
}
