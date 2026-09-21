job('ejemplo2-job-DSL'){
  description('Job DSL de ejemplo para el curso de Jenkins')
  scm{
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node -> 
      node / gitConfigName('francisco')
      node / gitConfigEmail('fcojrc@yahoo.com.mx')
    }
  }
  parameters{
    stringParam('nombre', defaultValue = 'Julian', description = 'Parámetro de cadena para el Job Booleano')
    choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
    booleanParam('agente', false)
  }
  triggers{
    cron('H/7 * * * * ')
  }
  steps{
    shell("bash jobscript.sh")
  }
  publishers{
    mailer('fcojrc@yahoo.com.mx', true, true)
    slackNotifier{
    notifyAborted(true)
    notifyEveryFailure(true)
    notifyNotBuilt(false)
    notifyUnstable(false)
    notifyBackToNormal(true)
    notifySuccess(false)
    notifyRepeatedFailure(false)
    startNotification(false)
    includeTestSummary(false)
    includeCustomMessage(false)
    customMessage(null)
    sendAs(null)
    commitInfoChoice('NONE')
    teamDomain(null)
    authToken(null)
    }
  }
}
