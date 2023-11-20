package com.wei.amazingtalker.feature.home.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wei.amazingtalker.core.designsystem.icon.AtIcons
import com.wei.amazingtalker.core.designsystem.theme.AtTheme
import com.wei.amazingtalker.feature.home.R
import com.wei.amazingtalker.feature.home.home.HomeViewState
import com.wei.amazingtalker.feature.home.home.loadImageUsingCoil
import com.wei.amazingtalker.feature.home.home.utilities.CARD_CORNER_SIZE
import com.wei.amazingtalker.feature.home.home.utilities.CLASS_NAME
import com.wei.amazingtalker.feature.home.home.utilities.ContactHeadShotSize
import com.wei.amazingtalker.feature.home.home.utilities.DEFAULT_SPACING
import com.wei.amazingtalker.feature.home.home.utilities.LARGE_SPACING
import com.wei.amazingtalker.feature.home.home.utilities.SKILL_LEVEL
import com.wei.amazingtalker.feature.home.home.utilities.SKILL_LEVEL_PROGRESS
import com.wei.amazingtalker.feature.home.home.utilities.SKILL_NAME
import com.wei.amazingtalker.feature.home.home.utilities.TUTOR_NAME

@Composable
fun MyCoursesTabContent(
    modifier: Modifier = Modifier,
    uiStates: HomeViewState,
    onCardClick: () -> Unit,
) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(LARGE_SPACING.dp))
            Row(modifier = modifier) {
                CourseProgressCard(
                    modifier = Modifier.weight(1f),
                    courseProgress = uiStates.courseProgress,
                    courseCount = uiStates.courseCount,
                    onClick = onCardClick,
                )
                Spacer(modifier = Modifier.width(DEFAULT_SPACING.dp))
                PupilRatingCard(
                    modifier = Modifier.weight(1f),
                    pupilRating = uiStates.pupilRating,
                    onClick = onCardClick,
                )
            }
            Spacer(modifier = Modifier.height(DEFAULT_SPACING.dp))
            TutorProfileCard(
                modifier = modifier,
                tutorName = uiStates.tutorName,
                className = uiStates.className,
                lessonsCountDisplay = uiStates.lessonsCountDisplay,
                ratingCount = uiStates.ratingCount,
                startedDate = uiStates.startedDate,
                onTutorClick = onCardClick,
                onClick = onCardClick,
            )
            Spacer(modifier = Modifier.height(DEFAULT_SPACING.dp))
            Row(modifier = modifier) {
                val cardSize = calculateCardSize()

                ContactListCard(modifier = Modifier.size(cardSize.dp))
                Spacer(modifier = Modifier.width(DEFAULT_SPACING.dp))
                SkillProgressCard(
                    modifier = Modifier
                        .size(cardSize.dp)
                        .weight(1f),
                    skillName = uiStates.skillName,
                    skillLevel = uiStates.skillLevel,
                    progress = uiStates.skillLevelProgress,
                    onClick = onCardClick,
                )
            }
            Spacer(modifier = Modifier.height(LARGE_SPACING.dp))
        }
    }
}

@Composable
fun CourseProgressCard(
    modifier: Modifier = Modifier,
    courseProgress: Int,
    courseCount: Int,
    onClick: () -> Unit,
) {
    val completed = stringResource(id = R.string.completed)

    StatusCard(
        modifier = modifier.semantics {
            contentDescription = completed
        },
        onClick = onClick,
        content = {
            Row {
                Text(
                    text = completed,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = " ($courseProgress%)",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = "$courseCount",
                style = MaterialTheme.typography.headlineSmall,
            )
        },
    )
}

@Composable
fun PupilRatingCard(
    modifier: Modifier = Modifier,
    pupilRating: Double,
    onClick: () -> Unit,
) {
    val pupil = stringResource(id = R.string.pupil)
    val rating = stringResource(id = R.string.rating)

    StatusCard(
        modifier = modifier
            .semantics {
                contentDescription = pupil + rating
            },
        onClick = onClick,
        content = {
            Row {
                Text(
                    text = pupil,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = " $rating",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = AtIcons.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(LARGE_SPACING.dp),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$pupilRating",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        },
    )
}

@Composable
fun TutorProfileCard(
    modifier: Modifier = Modifier,
    tutorName: String,
    className: String,
    lessonsCountDisplay: String,
    ratingCount: Double,
    startedDate: String,
    onTutorClick: () -> Unit,
    onClick: () -> Unit,
) {
    TutorCard(
        modifier = modifier,
        content = {
            TutorButton(
                tutorName = tutorName,
                onTutorClick = onTutorClick,
            )
            Spacer(modifier = Modifier.height(DEFAULT_SPACING.dp))
            ClassName(className = className)
            Spacer(modifier = Modifier.height(DEFAULT_SPACING.dp))
            ClassInfo(
                lessonsCountDisplay = lessonsCountDisplay,
                ratingCount = ratingCount,
                startedDate = startedDate,
            )
            Spacer(modifier = Modifier.height(DEFAULT_SPACING.dp))
        },
        onClick = onClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(size = CARD_CORNER_SIZE.dp),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            content = {
                content()
            },
        )
    }
}

@Composable
private fun ClassInfo(
    lessonsCountDisplay: String,
    ratingCount: Double,
    startedDate: String,
) {
    Row(
        modifier = Modifier.padding(horizontal = 8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            val lessons = stringResource(id = R.string.lessons)

            Text(
                text = lessons,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline,
            )
            Text(
                text = " $lessonsCountDisplay",
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            val rating = stringResource(id = R.string.rating)

            Text(
                text = rating,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline,
            )
            Text(
                text = " $ratingCount",
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            val started = stringResource(id = R.string.started)

            Text(
                text = started,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline,
            )
            Text(
                text = " $startedDate",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun ClassName(className: String) {
    Row(modifier = Modifier.padding(horizontal = 8.dp)) {
        Text(
            text = className,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1.5f),
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun TutorButton(
    tutorName: String,
    onTutorClick: () -> Unit,
) {
    val tutor = stringResource(id = R.string.tutor)

    Button(
        onClick = onTutorClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        contentPadding = ButtonDefaults.TextButtonContentPadding,
    ) {
        Icon(
            imageVector = AtIcons.Person,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp),
        )
        Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
        Text(
            text = tutor,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.outline,
        )
        Text(
            text = " $tutorName",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun ContactListCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(size = CARD_CORNER_SIZE.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSecondary,
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
    ) {
        Column(
            modifier = Modifier.padding(DEFAULT_SPACING.dp),
            content = {
                Row {
                    ContactHeadShot(
                        name = TUTOR_NAME,
                        avatarId = R.drawable.jamie_coleman,
                        isPreview = true,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    ContactHeadShot(
                        name = "contact tutor 1",
                        avatarId = R.drawable.img_face_01,
                        isPreview = true,
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    ContactHeadShot(
                        name = "contact tutor 2",
                        avatarId = R.drawable.img_face_02,
                        isPreview = true,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    ContactHeadShot(
                        name = "contact tutor 3",
                        avatarId = R.drawable.img_face_03,
                        isPreview = true,
                    )
                }
            },
        )
    }
}

@Composable
internal fun ContactHeadShot(
    modifier: Modifier = Modifier,
    avatarId: Int,
    name: String,
    isPreview: Boolean,
) {
    val painter = loadImageUsingCoil(avatarId, isPreview)

    val profilePictureDescription = stringResource(R.string.profile_picture).format(name)
    Image(
        painter = painter,
        contentDescription = profilePictureDescription,
        modifier = modifier
            .clip(CircleShape)
            .size(ContactHeadShotSize.dp),
    )
}

private fun calculateCardSize(): Int {
    return (ContactHeadShotSize * 2) + (DEFAULT_SPACING * 2) + 4
}

@Preview(showBackground = true)
@Composable
fun MyCoursesTabContentPreview() {
    AtTheme {
        MyCoursesTabContent(
            modifier = Modifier.padding(horizontal = LARGE_SPACING.dp),
            onCardClick = {},
            uiStates = HomeViewState(
                courseProgress = 20,
                courseCount = 30,
                pupilRating = 9.9,
                tutorName = TUTOR_NAME,
                className = CLASS_NAME,
                lessonsCountDisplay = "40+",
                ratingCount = 4.9,
                startedDate = "11.04",
                skillName = SKILL_NAME,
                skillLevel = SKILL_LEVEL,
                skillLevelProgress = SKILL_LEVEL_PROGRESS,
            ),
        )
    }
}
