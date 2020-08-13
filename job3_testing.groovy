job('job3_testing'){
description('this job is for testing' )
triggers {
        upstream('job2_deploy.', 'SUCCESS')
    }
steps{
shell('''nodeport=$(kubectl get svc -o jsonpath={.items[*].spec.ports[*].nodePort})
         status=$(curl -o /dev/null -s -w "%{http_code}" http://192.168.99.100:$nodeport)
         if [[ $status == 200 ]]
         then
         echo " deployed code is working fine "
         exit 0
         else 
         exit 1
         fi''')
}
publishers {
        downstreamParameterized {
            trigger('job4_notify_email') {
                condition('FAILED')
                triggerWithNoParameters()
                
               
            }}

}
}
