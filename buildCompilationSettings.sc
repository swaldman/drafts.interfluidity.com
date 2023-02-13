// huge thanks to @lolgab onn the Scala discord!

interp.configureCompiler { c =>
  val settings = c.settings
  settings.YtastyReader.value = true
}


