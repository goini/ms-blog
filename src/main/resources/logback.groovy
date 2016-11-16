import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO;

scan("5 minutes")
def LOG_PATH = "logs"
def LOG_ARCHIVE = "${LOG_PATH}/archive"
appender("Console-Appender", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level [%date{yyyy-mm-dd HH:mm:ss}] %logger{0} - %msg%n"
    }
}
appender("File-Appender", FileAppender) {
    file = "${LOG_PATH}/logfile.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%level[%date{ISO8601}] %logger{0} - %msg%n"
        outputPatternAsHeader = true

    }
}
//appender("RollingFile-Appender", RollingFileAppender) {
//    file = "${LOG_PATH}/rollingfile.log"
//    rollingPolicy(TimeBasedRollingPolicy) {
//        fileNamePattern = "${LOG_ARCHIVE}/rollingfile.log%d{yyyy-MM-dd}.log"
//        maxHistory = 30
//        totalSizeCap = "1KB"
//    }
//    encoder(PatternLayoutEncoder) {
//        pattern = "%msg%n"
//    }
//}
//appender("Async-Appender", AsyncAppender) {
//    appenderRef("RollingFile-Appender")
//}
logger("com.ms", DEBUG, ["Console-Appender", "File-Appender"], false)
root(INFO, ["Console-Appender"])