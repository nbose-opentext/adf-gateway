### Active Gateway Service 

Gateway service for the Active Data Foundry platform to be invoked from the TG16 orchestration flow.

---

#### Best practices to be followed

- No static method (difficulties in unit test)
- No power mock in unit testing.
- No use of System.currentTimeMillis(), prevent mocking in unit test, use java 8 clock (refer spring bean clock), can be easily mocked and unit tested
- JUnit 5 should be used (no Junit 4)
- Code coverage 90%
- Sonar lint plugin and checkstyle plugin should be integrated in idea.