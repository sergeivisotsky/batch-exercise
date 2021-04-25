@echo off

if "%1" == "all" (
    call docker stop postgres-tmp vsftpd-ftp batch-deployer batch-processing
    call docker rm -f postgres-tmp vsftpd-ftp batch-deployer batch-processing

    pushd ..\
        call mvn clean install -DskipTests -T1C
    popd

    call docker-compose -p batch-processing up -d --build
)
