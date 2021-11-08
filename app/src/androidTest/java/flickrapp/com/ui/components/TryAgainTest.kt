package flickrapp.com.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import flickrapp.com.ui.theme.FlickrAppTheme
import org.junit.Assert.*

import org.junit.Rule
import org.junit.Test

class TryAgainTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun runWithSuccess() {
        var isClicked = false
        composeTestRule.setContent {
            FlickrAppTheme {
                TryAgain {
                    isClicked = true
                }
            }
        }

        composeTestRule.onNodeWithText("Try again").performClick()
        assertTrue(isClicked)
    }
}