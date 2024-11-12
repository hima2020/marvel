import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SearchView(onWordChanged: ((String) -> Unit),onCancelClicked: (() -> Unit)) {
    val query = remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFFF0F0F0)) // Light background color
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Filled.Search, "Search Icon")
        Spacer(modifier = Modifier.width(8.dp))
        BasicTextField(
            value = query.value,
            onValueChange = {
                query.value = it
                onWordChanged?.invoke(it)
            },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp
            ),
            cursorBrush = SolidColor(Color.Gray),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            decorationBox = { innerTextField ->
                if (query.value.isEmpty()) {
                    Text(
                        text = "search for your Marvel...",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                innerTextField()
            }
        )

        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Cancel",
            color = Color.Red,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                onCancelClicked.invoke()
            }
        )
    }
}