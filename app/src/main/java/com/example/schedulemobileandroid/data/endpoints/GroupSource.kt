//package com.example.schedulemobileandroid.data.endpoints
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.schedulemobileandroid.data.networkServices.GroupApiService
//import com.example.schedulemobileandroid.domain.models.group.Group
//import retrofit2.HttpException
//import java.io.IOException
//
//class GroupSource(
//    private val groupNetworkService: GroupApiService,
//    private val search: String?
//) : PagingSource<Int, Group>() {
//
//    override fun getRefreshKey(state: PagingState<Int, Group>): Int? {
//        return state.anchorPosition
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Group> {
//        return try {
//            val nextPage = params.key ?: 1
//            val groupList = groupNetworkService.getGroupsByPage(
//                search = if (search.isNullOrEmpty()) null else search,
//                page = nextPage
//            )
//
//            LoadResult.Page(
//                data = groupList.data?.items ?: emptyList(),
//                prevKey = if (nextPage == 1) null else nextPage - 1,
//                nextKey = if (groupList.data?.items?.isEmpty() != false) null else groupList.data.pageNumber + 1
//            )
//        } catch (exception: IOException) {
//            return LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            return LoadResult.Error(exception)
//        }
//    }
//}