SBT Liquibase plugin
====================================

# Instructions for use:

### Step 1: Include the plugin in your build

Add the following to your `project/plugins.sbt`:

## sbt-0.13

    addSbtPlugin("co.pragmati" % "sbt-liquibase" % "0.6.6")

### Step 2: Add sbt-liquibase settings to your build

Add the following to your 'build.sbt' ( if you are using build.sbt )


    import com.github.bigtoast.sbtliquibase.LiquibasePlugin

    seq(LiquibasePlugin.liquibaseSettings: _*)
    
    liquibaseUsername := ""

    liquibasePassword := ""
                        
    liquibaseDriver   := "com.mysql.jdbc.Driver"
                        
    liquibaseUrl      := "jdbc:mysql://localhost:3306/test_db?createDatabaseIfNotExist=true"
    
    liquibaseChangelog := "src/main/liquibase/changelog/changelog-master.yml"

Or if you are using a build object extending from Build:

    import sbt._
    import Keys._
    import com.github.bigtoast.sbtliquibase.LiquibasePlugin._

    class MyBuildThatHasntDrankTheNoSQLKoolAid extends Build {
         lazy val seniorProject = Project("hola", file("."), settings = Defaults.defaultSettings ++ liquibaseSettings ++ Seq (
              liquibaseUsername := "dbusername",
              liquibasePassword := "kittensareevil"
              /* lots more liquibase settings available */
         )
    }


## Settings

Setting | Description 
--- | --- 
*liquibaseUsername* | Username for the database. This defaults to blank. (liquibaseUsername := "asdf")
*liquibasePassword* | Password for the database. This defaults to blank. (liquibasePassword := "secretstuff")
*liqubaseDriver* | Database driver classname. There is no default. (liquibaseDriver := "com.mysql.jdbc.Driver")
*liquibaseUrl* | Database connection uri. There is no default. (liquibaseUrl := "jdbc:mysql://localhost:3306/mydb")
*liquibaseChangelogCatalog* | Default catalog name for the changelog tables. This defaults to None. (liquibaseChangelogCatalog := Some("my_catalog"))
*liquibaseChangelogSchemaName* | Default schema name for the changelog tables. This defaults to None. (liquibaseChangelogSchemaName := Some("my_schema"))
*liquibaseDefaultCatalog* | Default catalog name for the db if it isn't defined in the uri. This defaults to None. (liquibaseDefaultCatalog := Some("my_catalog"))
*liquibaseDefaultSchemaName* | Default schema name for the db if it isn't defined in the uri. This defaults to None. (liquibaseDefaultSchemaName := Some("my_schema"))
*liquibaseChangelog* | Full path to your changelog file. This defaults 'src/main/migrations/changelog.xml'. (liquibaseChangelog := "other/path/dbchanges.xml")


## Tasks

Task | Description 
--- | --- 
*liquibase-update* | Run the liquibase migration
*liquibase-status* | Print count of yet to be run changesets
*liquibase-clear-checksums* | Removes all saved checksums from database log. Useful for 'MD5Sum Check Failed' errors
*liquibase-list-locks* | Lists who currently has locks on the database changelog
*liquibase-release-locks* | Releases all locks on the database changelog.
*liquibase-validate-changelog* | liquibase-validate-changelog
*liquibase-db-diff* | ( this isn't implemented yet ) Generate changeSet(s) to make Test DB match Development
*liquibase-db-doc* | Generates Javadoc-like documentation based on current database and change log
*liquibase-generate-changelog* | Writes Change Log XML to copy the current state of the database to the file defined in the changelog setting
*liquibase-changelog-sync-sql* | Writes SQL to mark all changes as executed in the database to STDOUT
*liquibase-tag* | Tags the current database state for future rollback with {tag}
*liquibase-rollback* | Rolls back the database to the the state is was when the {tag} was applied.
*liquibase-rollback-sql* | Writes SQL to roll back the database to that state it was in when the {tag} was applied to STDOUT
*liquibase-rollback-count* | Rolls back the last {int i} change sets applied to the database
*liquibase-rollback-sql-count* | Writes SQL to roll back the last {int i} change sets to STDOUT applied to the database
*liquibase-rollback-to-date* | Rolls back the database to the the state it was at the given date/time. Date Format: yyyy-MM-dd HH:mm:ss
*liquibase-rollback-to-date-sql { yyyy-MM-dd HH:mm:ss }* | Writes SQL to roll back the database to that state it was in at the given date/time version to STDOUT
*liquibase-future-rollback-sql* | Writes SQL to roll back the database to the current state after the changes in the changelog have been applied.
*liquibase-drop-all* | Drop all tables



Notes
------------------

- Liquibase version updated to 3.5.3
- Support for SBT 0.13
- Plugin released in sonatype
- Updated README


If any bugs are found or features wanted please file an issue in the github project. I will do my best to accommodate.


Acknoledgements
---------------
Forked from https://github.com/becompany/sbt-liquibase (https://github.com/bigtoast/sbt-liquibase)




