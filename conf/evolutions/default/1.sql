# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  user_id                   bigint,
  company_cnpj              varchar(255),
  privilege                 varchar(6),
  create_on                 timestamp,
  last_research_on          timestamp,
  constraint ck_account_privilege check (privilege in ('Owner','Admin','Viewer')),
  constraint pk_account primary key (user_id, company_cnpj))
;

create table company (
  cnpj                      varchar(14) not null,
  trade_name                varchar(255) not null,
  corporate_name            varchar(255) not null,
  departament               varchar(255) not null,
  contact                   varchar(255) not null,
  phone                     varchar(50) not null,
  employers                 integer,
  size                      varchar(11),
  constraint ck_company_size check (size in ('Individual','Micro','Small','Medium','MediumLarge','Large')),
  constraint pk_company primary key (cnpj))
;

create table user (
  id                        bigint auto_increment not null,
  name                      varchar(255) not null,
  email                     varchar(255) not null,
  password                  varchar(255) not null,
  phone                     varchar(50) not null,
  profile                   varchar(5),
  confirmed                 boolean default false not null,
  recovery_key              varchar(250),
  constraint ck_user_profile check (profile in ('ADMIN','USER')),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;

create sequence account_seq;

create sequence company_seq;

alter table account add constraint fk_account_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_account_user_1 on account (user_id);
alter table account add constraint fk_account_company_2 foreign key (company_cnpj) references company (cnpj) on delete restrict on update restrict;
create index ix_account_company_2 on account (company_cnpj);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists account;

drop table if exists company;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists account_seq;

drop sequence if exists company_seq;

