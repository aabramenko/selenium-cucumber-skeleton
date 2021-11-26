How to run:
===========

`mvn test -Dbrowser=chrome -Dheadless=true -Dsuite=example.xml`

Reporting:
==========

There are two types of report files:

[1] Cucumber native "pretty" report. 

This report is generated automatically after each maven run.

[2] Advanced HTML report by damianszczepanik (https://github.com/damianszczepanik/cucumber-reporting)

Run the following command in order to generate the advanced report: 

`mvn verify -DskipTests=true` 

All reports are saved in the "target" folder.

All reports have screenshots on test failures by default.