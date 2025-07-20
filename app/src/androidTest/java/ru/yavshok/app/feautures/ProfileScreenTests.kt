//package ru.yavshok.app.feautures
//
//import androidx.activity.ComponentActivity
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.platform.app.InstrumentationRegistry
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import ru.yavshok.app.ui.screens.profile.ProfileScreen
//import ru.yavshok.app.viewmodel.ProfileViewModel
//import ru.yavshok.app.data.model.User
//import ru.yavshok.app.data.repository.AuthRepository
//import ru.yavshok.app.data.repository.ExperimentRepository
//import ru.yavshok.app.data.storage.TokenStorage
//import ru.yavshok.app.data.store.UserStore
//import ru.yavshok.app.data.network.NetworkModule
//
//@RunWith(AndroidJUnit4::class)
//class ProfileScreenTests {
//
//    @get: Rule
//    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
//
//    // Здесь можно положить моковые данные пользователя
//    private var mockUser: User? = null // <-- сюда положи свои тестовые данные
//
//    // Фейковый UserStore для подмены данных
//    class FakeUserStore(private val user: User?) : UserStore(AuthRepository(NetworkModule.provideApiService()), TokenStorage(InstrumentationRegistry.getInstrumentation().targetContext)) {
//        override fun getUserImmediate(): User? = user
//        // Можно добавить другие методы, если потребуется
//    }
//
//    private lateinit var profileViewModel: ProfileViewModel
//
//    @Before
//    fun setUp() {
//        val context = InstrumentationRegistry.getInstrumentation().targetContext
//        val fakeUserStore = FakeUserStore(mockUser)
//        val tokenStorage = TokenStorage(context)
//        val authRepository = AuthRepository(NetworkModule.provideApiService())
//        val experimentRepository = ExperimentRepository(NetworkModule.provideApiService(), context)
//
//        profileViewModel = ProfileViewModel(
//            tokenStorage = tokenStorage,
//            authRepository = authRepository,
//            experimentRepository = experimentRepository,
//            userStore = fakeUserStore
//        )
//
//        composeTestRule.setContent {
//            ProfileScreen(
//                // TODO: Передай сюда свой PageObject, когда реализуешь
//                viewModel = profileViewModel
//            )
//        }
//    }
//
//    @Test
//    fun checkAllElementsOnProfileScreen() {
//        // TODO: Используй здесь свой PageObject для проверки элементов
//        // Пример:
//        // profileScreen.title.assertIsDisplayed()
//        // profileScreen.emailField.assertTextContains("test@mail.ru")
//    }
//}