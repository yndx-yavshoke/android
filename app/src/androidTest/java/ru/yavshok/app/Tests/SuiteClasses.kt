package ru.yavshok.app.Tests

import org.junit.runner.RunWith
import org.junit.runners.Suite
import ru.yavshok.app.Tests.LoginScreenTests.LoginScreenTests
import ru.yavshok.app.Tests.RegisterScreenTests.RegisterScreenTests

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainScreenTests::class,
    LoginScreenTests::class,
    RegisterScreenTests::class,
    ProfileScreenTests::class,
    ProfileScreenStatusTests::class
)
class AllTestsSuite
