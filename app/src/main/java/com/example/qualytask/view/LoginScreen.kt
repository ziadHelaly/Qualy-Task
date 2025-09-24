package com.example.qualytask.view

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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qualytask.R
import com.example.qualytask.data.CountryCode
import com.example.qualytask.viewModel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel,modifier: Modifier = Modifier) {
    var text: String by remember { mutableStateOf("") }
    var isCheck by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }
    val countryCodes by viewModel.countries.collectAsState()
    val selectedItem by viewModel.selectedCountry.collectAsState()

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

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
        ) {
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
                Row(
                    modifier = Modifier
                        .menuAnchor()
                        .height(56.dp)
                        .clickable { expanded = true }
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(selectedItem.flag),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(R.drawable.ic_down_arrow),
                            contentDescription = null
                        )
                        Text(selectedItem.dialCode)
                    }

                }
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
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                countryCodes.forEach { country ->
                    DropdownMenuItem(
                        text = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painterResource(country.flag),
                                    tint = Color.Unspecified,
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )
                                Text("${country.country} (${country.dialCode})")
                            }

                        },
                        onClick = {
                            viewModel.updateSelectedCountry(country)
                            expanded = false
                        }
                    )
                }

            }
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
            Text(
                "community guidelines",
                color = Color.Blue,
                maxLines = 1,
                fontSize = 14.sp,
                modifier = Modifier.clickable {})

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

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {

}