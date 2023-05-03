create table NAccount(
    na_seq int Not Null,
    name varchar(50) Not Null,
    password varchar(50),
    email varchar(50) Not Null unique,
    cellphone varchar(11),
    reg_date DATETIME Not Null,
    role varchar(10), -- 여기를 어떻게 넣을지
    primary key(na_seq)
);

create table NAccount_project(
    nap_seq int Not Null,
    --na_seq -- 외래키
    --np_seq -- 외래키
    primary key(nap_seq)
);

create table NBacklog(
    nb_seq int Not Null,
    title varchar(100) Not Null,
    story_progress int ,
    description varchar(255),
    assign int ,
    --np_seq -- 외래키
    primary key(nb_seq)
);
create table nproject(
    np_seq int not null ,
    project_title varchar(100) not null ,
    project_assign varchar(100),
    primary key (np_seq)
);

create table NTask(
    nt_seq int Not NULL,
    title VARCHAR(25) unique,
    story_progress int,
    description varchar(255) ,
    assign int,
    create_date DATETIME ,
    update_date DATETIME,
    presenter int NOT NULL ,
    manager int NOT NULL ,
    --nq_seq, --외래키
    --np_seq, --외래키
    --nb_seq  --외래키
    primary key (nt_seq)
);