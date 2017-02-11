/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     6/30/2014 7:29:36 PM                         */
/*==============================================================*/


if exists (select 1
            from  sysobjects
           where  id = object_id('TIM')
            and   type = 'U')
   drop table TIM


if exists (select 1
            from  sysobjects
           where  id = object_id('UTAKMICE')
            and   type = 'U')
   drop table UTAKMICE


/*==============================================================*/
/* Table: TIM                                                   */
/*==============================================================*/
create table TIM (
   ID_TIMA              int    IDENTITY(1,1)              not null,
   NAZIV_TIMA           varchar(255)             not null UNIQUE,
   PRVI_PUT_IGRAO_NA_SP datetime             not null,
   NAJVECI_USPEH        varchar(255)         not null,
   BROJ_UCESCA          int                  not null,
   constraint PK_TIM primary key nonclustered (ID_TIMA)
)


/*==============================================================*/
/* Table: UTAKMICE                                              */
/*==============================================================*/
create table UTAKMICE (
   ID_UTAKMICE          int   IDENTITY(1,1)               not null,
   GOL_GOSTI            int                  not null,
   NAZIV_TIMA_GOSTI           varchar(255)             not null,
   NAZIV_TIMA_DOMACI           varchar(255)             not null,
   GOL_DOMACINI         int                  not null,
   BROJ_ZUTIH_KARTONA   int                  not null,
   BROJ_CRVENIH_KARTONA int                  not null,
   STADION              varchar(255)         not null,
   DATUM_VREME          datetime             not null,
   constraint PK_UTAKMICE primary key nonclustered (ID_UTAKMICE, NAZIV_TIMA_GOSTI, NAZIV_TIMA_DOMACI)
)


