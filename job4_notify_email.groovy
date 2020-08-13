job('job4_notify_email'){
description(' this job will send email to developers ')
 
publishers {
        extendedEmail {
            recipientList('piyushmehta9909@gmail.com')
            defaultSubject('Oops')
            defaultContent('code is not working.Check code')
            contentType('text/html')
            triggers {
              always(){
          sendTo{
            recipientList()
                }
            }
        }
    }
}

}
