job('job2_deploy.'){
description('this job will deploy code')
triggers {
        upstream('seed_job', 'SUCCESS')
    }

steps {
shell('''if ! kubectl get pvc | grep php-deploy-pvc
         then
          kubectl create -f /root/.jenkins/workspace/seed_job/php_deploy_pvc.yml
         fi
         if ls /root/.jenkins/workspace/seed_job/ | grep .php
         then
          if kubectl get deploy | grep php-deploy
          then 
           PODS=$(kubectl get pods -l app=php -o jsonpath="{.items[*].metadata.name}")
           for i in $PODS
            do 
             kubectl cp /root/.jenkins/workspace/seed_job/*.php $i:/var/www/html/
            done
          else
           kubectl  create -f /root/.jenkins/workspace/seed_job/php_deploy.yml
           sleep 25
           PODS=$(kubectl get pods -l app=php -o jsonpath="{.items[*].metadata.name}")
           for i in $PODS
            do 
             kubectl cp /root/.jenkins/workspace/seed_job/*.php $i:/var/www/html/
            done
          fi 
         fi  
         if ! kubectl get svc | grep php-deploy-svc
         then
          kubectl create -f /root/.jenkins/workspace/seed_job/php_deploy_svc.yml
         fi''') 
}
} 
