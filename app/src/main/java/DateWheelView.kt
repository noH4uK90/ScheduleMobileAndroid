import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.schedulemobileandroid.presentation.viewModels.DateWheelViewModel
import com.google.accompanist.pager.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateWheelView(
    initialDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val viewModel: DateWheelViewModel = hiltViewModel()
    LaunchedEffect(initialDate) {
        viewModel.updateCurrentDate(initialDate)
    }
    val currentDate by viewModel.currentDate.collectAsState()
    val weekSlider by viewModel.weekSlider.collectAsState()
    val currentWeekIndex by viewModel.currentWeekIndex.collectAsState()

    DateWheelContent(
        currentDate = currentDate,
        weekSlider = weekSlider,
        currentWeekIndex = currentWeekIndex,
        onDateSelected = {
            viewModel.updateCurrentDate(it)
            onDateSelected(it)
        },
        onWeekIndexChanged = { viewModel.updateCurrentWeekIndex(it) }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DateWheelContent(
    currentDate: LocalDate,
    weekSlider: List<List<LocalDate>>,
    currentWeekIndex: Int,
    onDateSelected: (LocalDate) -> Unit,
    onWeekIndexChanged: (Int) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = currentWeekIndex)

    LaunchedEffect(pagerState.currentPage) {
        onWeekIndexChanged(pagerState.currentPage)
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        HeaderView(currentDate)
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalPager(
            count = weekSlider.size,
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            WeekView(weekSlider[page], currentDate, onDateSelected)
        }
    }
}

@Composable
fun HeaderView(currentDate: LocalDate) {
    Column {
        Text(
            text = currentDate.format(DateTimeFormatter.ofPattern("LLLL")),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = currentDate.format(DateTimeFormatter.ofPattern("YYYY")),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = currentDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM")),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun WeekView(
    week: List<LocalDate>,
    currentDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    Row {
        week.forEach { day ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        if (day == currentDate) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(8.dp)
                    .clickable { onDateSelected(day) }
            ) {
                Text(
                    text = day.format(DateTimeFormatter.ofPattern("E")),
                    style = MaterialTheme.typography.bodySmall,
                    color = if (day == currentDate) Color.White else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = day.format(DateTimeFormatter.ofPattern("dd")),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (day == currentDate) Color.White else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}