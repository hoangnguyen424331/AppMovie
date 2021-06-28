package com.example.appmovie.movie

import com.example.appmovie.data.model.Actor
import com.example.appmovie.data.model.DetailMovie
import com.example.appmovie.data.model.HotMovie
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.data.source.repository.FavoriteRepository
import com.example.appmovie.data.source.repository.MovieRepository
import com.example.appmovie.ui.detail.DetailMovieContact
import com.example.appmovie.ui.detail.DetailMoviePresenter
import com.example.appmovie.utils.FakeData
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.verify
import java.lang.Exception

@Suppress("UNCHECKED_CAST")
@RunWith(MockitoJUnitRunner::class)
class MovieDetailPresenterTest {

    @Mock
    private lateinit var view: DetailMovieContact.View

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var favoriteRepository: FavoriteRepository

    @Mock
    private lateinit var exception: Exception

    private lateinit var presenter: DetailMoviePresenter
    private lateinit var listActor: List<Actor>
    private lateinit var listRecommend: List<HotMovie>

    private val detail = FakeData.DETAIL_MOVIE_MODEL

    @Before
    fun setUp() {
        presenter = DetailMoviePresenter(repository, favoriteRepository)
        presenter.onView(view)
        listActor = mutableListOf()
        listRecommend = mutableListOf()
    }

    @Test
    fun `get data detail success`() {
        `when`(
            repository.getDetailMovie(
                ArgumentMatchers.anyInt(),
                any()
            )
        ).doAnswer {
            (it.arguments[1] as OnFetchDataJsonListener<DetailMovie>).onSuccess(detail)
        }
        presenter.getMovieDetail(FakeData.ID_MOVIE_DETAIL)
        verify(view).loadContentMovieOnSuccess(detail)
    }

    @Test
    fun `get data detail error`() {
        `when`(
            repository.getDetailMovie(
                ArgumentMatchers.anyInt(),
                any()
            )
        ).doAnswer {
            (it.arguments[1] as OnFetchDataJsonListener<DetailMovie>).onError(exception)
        }
        presenter.getMovieDetail(FakeData.ID_MOVIE_DETAIL)
        verify(view).onError(exception)
    }

    @Test
    fun `get data actor success`() {
        `when`(
            repository.getActor(
                ArgumentMatchers.anyInt(),
                any()
            )
        ).doAnswer {
            (it.arguments[1] as OnFetchDataJsonListener<List<Actor>>).onSuccess(listActor)
        }
        presenter.getActor(FakeData.ID_MOVIE_DETAIL)
        verify(view).loadListActorOnSuccess(listActor)
    }

    @Test
    fun `get data actor error`() {
        `when`(
            repository.getActor(
                ArgumentMatchers.anyInt(),
                any()
            )
        ).doAnswer {
            (it.arguments[1] as OnFetchDataJsonListener<List<Actor>>).onError(exception)
        }
        presenter.getActor(FakeData.ID_MOVIE_DETAIL)
        verify(view).onError(exception)
    }

    @Test
    fun `get data recommend success`() {
        `when`(
            repository.getRecommend(
                ArgumentMatchers.anyInt(),
                any()
            )
        ).doAnswer {
            (it.arguments[1] as OnFetchDataJsonListener<List<HotMovie>>).onSuccess(listRecommend)
        }
        presenter.getListRecommend(FakeData.ID_MOVIE_DETAIL)
        verify(view).loadRecommendOnSuccess(listRecommend)
    }

    @Test
    fun `get data recommend error`() {
        `when`(
            repository.getRecommend(
                ArgumentMatchers.anyInt(),
                any()
            )
        ).doAnswer {
            (it.arguments[1] as OnFetchDataJsonListener<List<HotMovie>>).onError(exception)
        }
        presenter.getListRecommend(FakeData.ID_MOVIE_DETAIL)
        verify(view).onError(exception)
    }
}
