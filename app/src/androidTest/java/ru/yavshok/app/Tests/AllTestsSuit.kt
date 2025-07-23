package ru.yavshok.app.Tests

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses
import ru.yavshok.app.Tests.EditScreenTests.EditScreenActivityTests
import ru.yavshok.app.Tests.LoginScreenTests.LoginScreenActivityTests
import ru.yavshok.app.Tests.LoginScreenTests.LoginScreenTest
import ru.yavshok.app.Tests.MainScreenTests.MainScreenActivityTests
import ru.yavshok.app.Tests.MainScreenTests.MainScreenTest
import ru.yavshok.app.Tests.ProfileScreenTests.ProfileScreenActivityTests
import ru.yavshok.app.Tests.RegisterScrenTests.RegisterScreenActivityTests
import ru.yavshok.app.Tests.RegisterScrenTests.RegisterScreenTests

@RunWith(Suite::class)
@SuiteClasses(
    MainScreenTest::class,
    MainScreenActivityTests::class,
    LoginScreenTest::class,
    LoginScreenActivityTests::class,
    RegisterScreenTests::class,
    RegisterScreenActivityTests::class,
    ProfileScreenActivityTests::class,
    EditScreenActivityTests::class
)
class AllTestsSuit