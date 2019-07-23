How to run:
===========

simple way: ```mvn test```

run with parameters:

```mvn test -Drun_config=run_config_file -Denv_config=env_config_file -Dbrowser=chrome -Dheadless=true -Dsuite=suitefile.xml -Ddb_config=db_config_file -Dcreds=creds_file```

where:

---------------

-Drun_config=run_config_file: name of ".conf" file with high level test execution parameters such as paths

Default value: run_config_main.conf

---------------

-Denv_config=env_config_file: name of ".conf" file with test execution parameters such as browser

Default value: env_config_main.conf

---------------

-Dbrowser=chrome: the browser type what will be used for test run

Possible values: "chrome", "firefox"

Default value: defined in the run_config file

---------------

-Dheadless=true: set this parameter to "true" if you want to launch a browser in headless mode

Default value: defined in the run_config file

---------------

-Dsuite=suitefile.xml: testNG suite file

Default value: defined in the POM file

---------------

-Ddb_config=db_config_file: name of ".conf" file with a db connection parameters

Default value: db_config_main.conf

---------------

-Dcreds=creds_file: name of ".conf" file with test users credentials

Default value: creds_main.conf

---------------

Reporting:
==========

For now there are two types of report files:

1. Cucumber native "pretty" report

The report is generated automatically after each maven run

2. Advanced HTML report by damianszczepanik (https://github.com/damianszczepanik/cucumber-reporting)

In order to generate the report execute the following commands:

```mvn test ...```

```mvn verify -DskipTests=true``` 

All reports have screenshots on test failures by default.

All reports are saved in the "target" folder.


Plan:

- [Done] screenshots
- [Done] improve Gherkin rules
- [Done] data provider
- [Done] "OR"
- [Done] parallel testing
- [Done] Readme
- allure
- set # of threads for parallel testing
