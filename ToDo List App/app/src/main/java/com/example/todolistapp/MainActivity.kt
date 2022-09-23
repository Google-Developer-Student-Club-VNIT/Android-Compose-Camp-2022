package com.example.todolistapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.ui.theme.ToDoListAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ToDoList()
                }
            }
        }
    }
}

@Composable
fun ToDoItem(toDo: String, onValueChange: (String) -> Unit, onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(text = "Add to do item",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 25.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = toDo,
                onValueChange = onValueChange,
                //label = {Text("Add to do item", color = Color.Black)},
                singleLine = true,
                modifier = Modifier
                    .height(60.dp)
                    .width(240.dp)
                    .border(1.dp, Color.Black)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            FloatingActionButton(
                onClick = onButtonClick,
                backgroundColor = Color.Cyan,
                contentColor = Color.Red
            ) {
                Icon(Icons.Filled.Add,"")
            }
        }
    }
}

@Composable
fun ToDoListOneItem(value : String, onButtonClick: (String) -> Unit){
    Box(
        modifier = Modifier
            .width(300.dp)
            .padding(start = 10.dp, top = 20.dp, end = 10.dp)
            .border(1.dp, Color.Black)
    ){
        Text(
            text = value,
            modifier = Modifier
                .height(30.dp)
                .width(300.dp)
                .padding(start = 5.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Start
        )
        Image(
            painter = painterResource(id = R.drawable.delete),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(
                    enabled = true,
                    onClick = {onButtonClick(value)}
                )
                .padding(end = 2.dp)
        )
    }
}

@Composable
fun ToDoItemList(toDoList : ArrayList<String>, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 20.dp, start = 40.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "To Do items",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
        for(item in toDoList){
            ToDoListOneItem(value = item,
                onButtonClick = onClick
            )
        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ToDoList() {
    var toDo by remember { mutableStateOf("") }
    val toDoList by remember { mutableStateOf(ArrayList<String>()) }
    val onClick: (value:String) -> Unit = {
        toDoList.remove(it)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        ToDoItem(toDo = toDo,
            onValueChange = {toDo = it},
            onButtonClick = {
                if(toDo != "") {
                    toDoList.add(toDo)
                    toDo = ""
                }
            }
        )
        ToDoItemList(toDoList = toDoList,
            onClick = onClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDoListAppTheme {
        ToDoList()
    }
}