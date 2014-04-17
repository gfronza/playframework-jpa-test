# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  company_cnpj              varchar(14),
  privilege                 varchar(5),
  constraint ck_account_privilege check (privilege in ('One','Two','Three')),
  constraint uq_account_1 unique (user_id,company_cnpj),
  constraint pk_account primary key (id))
;

create table company (
  cnpj                      varchar(14) not null,
  trade_name                varchar(255) not null,
  corporate_name            varchar(255) not null,
  departament               varchar(255) not null,
  contact                   varchar(255) not null,
  phone                     varchar(50) not null,
  employers                 integer,
  constraint pk_company primary key (cnpj))
;

create table user (
  id                        bigint auto_increment not null,
  name                      varchar(255) not null,
  email                     varchar(255) not null,
  password                  varchar(255) not null,
  phone                     varchar(50) not null,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;

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

drop sequence if exists company_seq;

