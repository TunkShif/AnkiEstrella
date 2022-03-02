package one.tunkshif.ankiestrella.ui.query

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import one.tunkshif.ankiestrella.ui.theme.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(
    tabItems: List<String>,
    content: @Composable (index: Int) -> Unit
) {
    Column {
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(initialPage = 0)
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            backgroundColor = MaterialTheme.colors.background,
            contentColor = AnkiBlue100,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .height(4.dp)
                        .padding(horizontal = 28.dp)
                        .background(color = AnkiBlue100, shape = Rounded.large)
                )
            },
            divider = {},
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = AnkiBlue100,
                    unselectedContentColor = Gray200,
                    text = {
                        Text(text = item, fontFamily = fontOutfit, fontSize = 18.sp)
                    }
                )
            }
        }
        Divider(
            color = Gray50,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        HorizontalPager(
            count = tabItems.size,
            state = pagerState,
        ) { page ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(
                        start = Dimension.extraSmall,
                        end = Dimension.extraSmall,
                        top = Dimension.tiny
                    )
            ) {
                content(page)
            }
        }
    }
}