package ru.yavshok.app.Tests

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses
import ru.yavshok.app.Tests.EditScreenTests.EditActivityTests
import ru.yavshok.app.Tests.LoginScreenTests.LoginActivityTests
import ru.yavshok.app.Tests.LoginScreenTests.LoginTest
import ru.yavshok.app.Tests.MainScreenTests.MainActivityTests
import ru.yavshok.app.Tests.MainScreenTests.MainTest
import ru.yavshok.app.Tests.ProfileScreenTests.ProfileActivityTests
import ru.yavshok.app.Tests.RegisterScrenTests.RegisterActivityTests
import ru.yavshok.app.Tests.RegisterScrenTests.RegisterTests

@RunWith(Suite::class)
@SuiteClasses(
    MainTest::class,
    MainActivityTests::class,
    LoginTest::class,
    LoginActivityTests::class,
    RegisterTests::class,
    RegisterActivityTests::class,
    ProfileActivityTests::class,
    EditActivityTests::class
)
class StartAllTests

