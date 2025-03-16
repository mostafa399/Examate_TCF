package com.mstfahlal.examate_tcf.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.mstfahlal.examate_tcf.domain.model.connectios.ConnectItemModel
import com.mstfahlal.examate_tcf.ui.theme.Examate_TCFTheme
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun ConnectCardItem(
    itemModel: ConnectItemModel
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = Examate_TCFTheme.color.white),
        shape = Examate_TCFTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.sdp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.sdp),
            verticalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 7.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.sdp)
            ) {
                ProfilePicture(
                    image = itemModel.image,
                    size = 70
                )
                BodyTitleItem(
                    name = itemModel.userName,
                    target = itemModel.target,
                    items = itemModel.languages
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 7.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.sdp)
            ) {
                itemModel.userInfo.forEach {
                    ConnectTextItem(icon = it.icon!!, title = it.title)
                }
            }
        }
    }

}


@Composable
private fun ProfilePicture(
    modifier: Modifier = Modifier,
    image: Any? = null,
    size: Int,
    contentScale: ContentScale = ContentScale.Crop,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        contentDescription = "Profile Picture",
        contentScale = contentScale,
        loading = {
            CircularProgressIndicator()
        },
        modifier = modifier
            .clip(CircleShape)
            .background(Examate_TCFTheme.color.primary600)
            .size(size.sdp)
    )

}

@Composable
private fun BodyTitleItem(
    name: String,
    target: String,
    items: List<String>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(3.sdp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.sdp)
        ) {
            Text(
                text = name,
                textAlign = TextAlign.Start,
                style = Examate_TCFTheme.typography.bold18,
                color = Examate_TCFTheme.color.primary600
            )
            Box(
                modifier = Modifier
                    .clip(Examate_TCFTheme.shapes.medium)
                    .width(100.sdp)
                    .height(30.sdp)
                    .padding(2.sdp)
                    .background(Examate_TCFTheme.color.primary600),
                contentAlignment = Center
            ) {
                Text(
                    text = "Targeting: $target",
                    style = Examate_TCFTheme.typography.medium14,
                    color = Examate_TCFTheme.color.white,
                )
            }
        }
        Text(
            text = "Last seen online: Yesterday",
            textAlign = TextAlign.Start,
            style = Examate_TCFTheme.typography.medium14,
            color = Examate_TCFTheme.color.primary200,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.sdp),
        ) {
            items.forEach {
                Box(
                    modifier = Modifier
                        .clip(Examate_TCFTheme.shapes.medium)
                        .width(55.sdp)
                        .height(20.sdp)
                        .padding(2.sdp)
                        .background(Examate_TCFTheme.color.secondary400),
                    contentAlignment = Center
                ) {
                    Text(
                        text = it,
                        style = Examate_TCFTheme.typography.medium10,
                        color = Examate_TCFTheme.color.primary600,
                    )
                }
            }
        }
    }
}

@Composable
private fun ConnectTextItem(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier
            .padding(3.sdp)
            .height(30.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.sdp)
    ) {
        Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(16.sdp))
        Text(text = title, style = Examate_TCFTheme.typography.medium12, color = Examate_TCFTheme.color.primary200)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreivewItem() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BodyTitleItem(
            name = "Mostafa",
            target = "A1",
            items = listOf("English", "Arabic", "French")
        )
    }
}