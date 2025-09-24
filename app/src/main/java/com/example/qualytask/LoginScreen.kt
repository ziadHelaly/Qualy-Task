package com.example.qualytask

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import kotlin.math.roundToInt

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var text: String by remember { mutableStateOf("") }
    var isCheck by remember { mutableStateOf(true) }
    var isExpanded by remember { mutableStateOf(false) }
    var offsetX by remember { mutableStateOf(0.dp) }
    var offsetY by remember { mutableStateOf(0.dp) }


    val density = LocalDensity.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text("One last step ", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.size(8.dp))
        Text(buildAnnotatedString {
            append("Please login or signup for a\n ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Free ")
            }
            append("account to continue")
        })

        Spacer(modifier = Modifier.size(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(16.dp),
                    color = Color.Gray.copy(alpha = 0.4f)
                )
        ) {
            /*Row(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .onGloballyPositioned { coordinates ->
                        val position = coordinates.localToWindow(Offset.Zero)
                        with(density) {
                            offsetX = position.x.toDp()
                            offsetY = (position.y + coordinates.size.height).toDp()
                        }
                    }
                    .clickable {
                        isExpanded = true
                    }
            ) {
                Icon(
                    painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(30.dp)
                )
                Icon(
                    painterResource(R.drawable.ic_down_arrow),
                    contentDescription = null,
                )
            }*/
            CountryCodeDropdown()

            OutlinedTextField(
                text,
                onValueChange = { text = it },
                placeholder = { Text("7xxxxxxxx") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
            )
        }
        Text(
            "We will use this to verify your account",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray.copy(alpha = 0.7f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = isCheck, onCheckedChange = { isCheck = !isCheck })
            Text("I agree and comply to the ", fontSize = 14.sp)
            Text("community guidelines", color = Color.Blue, maxLines = 1, fontSize = 14.sp ,modifier = Modifier.clickable {})

        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {}, modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue")
                Icon(painterResource(R.drawable.ic_left_arrow), contentDescription = null)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodeDropdown() {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("+44") }

    val countryCodes = listOf(
        CountryCode("🇺🇸", "United States", "+1"),
        CountryCode("🇬🇧", "United Kingdom", "+44"),
        CountryCode("🇮🇳", "India", "+91"),
        CountryCode("🇨🇦", "Canada", "+1"),
        CountryCode("🇩🇪", "Germany", "+49"),
        CountryCode("🇫🇷", "France", "+33"),
        CountryCode("🇸🇦", "Saudi Arabia", "+966"),
        CountryCode("🇯🇵", "Japan", "+81"),
        CountryCode("🇨🇳", "China", "+86"),
        CountryCode("🇧🇷", "Brazil", "+55")
    )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        // Row trigger
        Row(
            modifier = Modifier
                .menuAnchor() // anchor this to the dropdown
                .height(56.dp)
                .clickable { expanded = true },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_down_arrow),
                    contentDescription = null
                )
                Text(selectedItem)
            }

        }

        // Dropdown menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            countryCodes.forEach { country ->
                DropdownMenuItem(
                    text = {
                        Text("${country.flag} ${country.country} (${country.dialCode})")
                    },
                    onClick = {
                        selectedItem = country.dialCode
                        expanded = false
                    }
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}