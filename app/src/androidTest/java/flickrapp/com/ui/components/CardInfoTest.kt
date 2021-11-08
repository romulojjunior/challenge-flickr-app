package flickrapp.com.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import flickrapp.com.ui.theme.FlickrAppTheme
import org.junit.Rule

import org.junit.Test

class CardInfoTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun runWithSuccess() {
        composeTestRule.setContent {
            FlickrAppTheme {
                CardInfo(title = "CardTitle", content = "CardContent")
            }
        }

        composeTestRule.onNodeWithText("CardTitle").assertIsDisplayed()
        composeTestRule.onNodeWithText("CardContent").assertIsDisplayed()
    }
}