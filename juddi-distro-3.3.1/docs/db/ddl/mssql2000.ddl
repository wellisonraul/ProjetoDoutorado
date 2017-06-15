
    create table j3_address (
        id number(19,0) identity not null,
        sort_code varchar2(10 char),
        tmodel_key varchar2(255 char),
        use_type varchar2(255 char),
        address_id number(19,0) not null,
        primary key (id)
    );

    create table j3_address_line (
        id number(19,0) identity not null,
        key_name varchar2(255 char),
        key_value varchar2(255 char),
        line varchar2(80 char) not null,
        address_id number(19,0) not null,
        primary key (id)
    );

    create table j3_auth_token (
        auth_token varchar2(51 char) not null,
        ipaddress varchar2(51 char),
        authorized_name varchar2(255 char) not null,
        created timestamp not null,
        last_used timestamp not null,
        number_of_uses number(10,0) not null,
        token_state number(10,0) not null,
        primary key (auth_token)
    );

    create table j3_binding_category_bag (
        id number(19,0) not null,
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_binding_descr (
        id number(19,0) identity not null,
        descr varchar2(255 char) not null,
        lang_code varchar2(26 char),
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_binding_template (
        access_point_type varchar2(255 char),
        access_point_url long,
        hosting_redirector varchar2(255 char),
        entity_key varchar2(255 char) not null,
        service_key varchar2(255 char) not null,
        primary key (entity_key)
    );

    create table j3_business_category_bag (
        id number(19,0) not null,
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_business_descr (
        id number(19,0) identity not null,
        descr varchar2(255 char) not null,
        lang_code varchar2(26 char),
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_business_entity (
        entity_key varchar2(255 char) not null,
        primary key (entity_key)
    );

    create table j3_business_identifier (
        id number(19,0) identity not null,
        key_name varchar2(255 char),
        key_value varchar2(255 char) not null,
        tmodel_key_ref varchar2(255 char),
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_business_name (
        id number(19,0) identity not null,
        lang_code varchar2(26 char),
        name varchar2(255 char) not null,
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_business_service (
        entity_key varchar2(255 char) not null,
        business_key varchar2(255 char) not null,
        primary key (entity_key)
    );

    create table j3_canonicalization_method (
        id number(19,0) identity not null,
        algorithm varchar2(255 char),
        primary key (id)
    );

    create table j3_category_bag (
        id number(19,0) identity not null,
        primary key (id)
    );

    create table j3_chg_graph (
        j3_id number(19,0) not null,
        primary key (j3_id)
    );

    create table j3_chg_graph_j3_ctrl_msg (
        j3_chg_graph_j3_id number(19,0) not null,
        controlMessage_id number(19,0) not null
    );

    create table j3_chg_graph_j3_edge (
        j3_chg_graph_j3_id number(19,0) not null,
        edge_id number(19,0) not null
    );

    create table j3_chg_graph_j3_node (
        j3_chg_graph_j3_id number(19,0) not null,
        node_name varchar2(255 char) not null
    );

    create table j3_chg_record (
        id number(19,0) not null,
        change_contents blob,
        entity_key varchar2(255 char),
        node_id varchar2(255 char),
        orginating_usn number(19,0),
        record_type number(10,0),
        primary key (id)
    );

    create table j3_chg_replconf (
        serialnumb number(19,0) not null,
        maxgettime number(19,2),
        maxsynctime number(19,2),
        configupdate varchar2(255 char),
        communicationGraph_j3_id number(19,0),
        contact_id number(19,0),
        primary key (serialnumb)
    );

    create table j3_chg_replconf_j3_operator (
        j3_chg_replconf_serialnumb number(19,0) not null,
        operator_operatorNodeID varchar2(255 char) not null
    );

    create table j3_clerk (
        clerk_name varchar2(255 char) not null,
        cred varchar2(255 char),
        publisher_id varchar2(255 char) not null,
        nodeid varchar2(255 char) not null,
        primary key (clerk_name)
    );

    create table j3_client_subscriptioninfo (
        subscription_key varchar2(255 char) not null,
        last_notified timestamp,
        fromClerk_clerk_name varchar2(255 char),
        toClerk_clerk_name varchar2(255 char),
        primary key (subscription_key)
    );

    create table j3_contact (
        id number(19,0) identity not null,
        use_type varchar2(255 char),
        entity_key varchar2(255 char),
        primary key (id)
    );

    create table j3_contact_descr (
        id number(19,0) identity not null,
        descr varchar2(255 char) not null,
        lang_code varchar2(26 char),
        contact_id number(19,0) not null,
        primary key (id)
    );

    create table j3_ctrl_msg (
        id number(19,0) not null,
        j3_message varchar2(255 char),
        primary key (id)
    );

    create table j3_discovery_url (
        id number(19,0) identity not null,
        url long not null,
        use_type varchar2(255 char) not null,
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_edge (
        id number(19,0) identity not null,
        communicationGraph_j3_id number(19,0),
        messageReceiver_name varchar2(255 char),
        messageSender_name varchar2(255 char),
        primary key (id)
    );

    create table j3_edge_j3_ctrl_msg (
        j3_edge_id number(19,0) not null,
        messages_id number(19,0) not null
    );

    create table j3_edge_j3_node (
        j3_edge_id number(19,0) not null,
        messageReceiverAlternate_name varchar2(255 char) not null,
        primary key (j3_edge_id, messageReceiverAlternate_name)
    );

    create table j3_email (
        id number(19,0) identity not null,
        email_address long not null,
        use_type varchar2(255 char),
        contact_id number(19,0) not null,
        primary key (id)
    );

    create table j3_instance_details_descr (
        id number(19,0) identity not null,
        descr varchar2(255 char) not null,
        lang_code varchar2(26 char),
        tmodel_instance_info_id number(19,0) not null,
        primary key (id)
    );

    create table j3_instance_details_doc_descr (
        id number(19,0) identity not null,
        descr varchar2(255 char) not null,
        lang_code varchar2(26 char),
        tmodel_instance_info_id number(19,0) not null,
        primary key (id)
    );

    create table j3_key_data_value (
        id number(19,0) identity not null,
        key_data_name varchar2(255 char),
        key_data_type varchar2(255 char),
        key_data_value blob,
        key_data_value_string clob,
        key_data_value_key number(19,0),
        key_info_key number(19,0),
        primary key (id)
    );

    create table j3_key_info (
        id number(19,0) identity not null,
        xml_id varchar2(255 char),
        primary key (id)
    );

    create table j3_keyed_reference (
        id number(19,0) identity not null,
        key_name varchar2(255 char),
        key_value varchar2(255 char) not null,
        tmodel_key_ref varchar2(255 char),
        category_bag_id number(19,0),
        keyed_reference_group_id number(19,0),
        primary key (id)
    );

    create table j3_keyed_reference_group (
        id number(19,0) identity not null,
        tmodel_key varchar2(255 char),
        category_bag_id number(19,0) not null,
        primary key (id)
    );

    create table j3_node (
        name varchar2(255 char) not null,
        client_name varchar2(255 char) not null,
        custody_transfer_url varchar2(255 char) not null,
        factory_initial varchar2(255 char),
        factory_naming_provider varchar2(255 char),
        factory_url_pkgs varchar2(255 char),
        inquiry_url varchar2(255 char) not null,
        juddi_api_url varchar2(255 char),
        proxy_transport varchar2(255 char) not null,
        publish_url varchar2(255 char) not null,
        replication_url varchar2(255 char),
        security_url varchar2(255 char) not null,
        subscriptionlist_url varchar2(255 char) not null,
        subscription_url varchar2(255 char) not null,
        primary key (name)
    );

    create table j3_node_j3_clerk (
        j3_node_name varchar2(255 char) not null,
        clerks_clerk_name varchar2(255 char) not null
    );

    create table j3_object_type (
        id number(19,0) identity not null,
        encoding varchar2(255 char),
        mime_type varchar2(255 char),
        xml_id varchar2(255 char),
        signature_key number(19,0) not null,
        primary key (id)
    );

    create table j3_object_type_content (
        id number(19,0) identity not null,
        content blob,
        object_type_key number(19,0) not null,
        primary key (id)
    );

    create table j3_operator (
        operatorNodeID varchar2(255 char) not null,
        operator_status number(10,0),
        replicationurl varchar2(255 char),
        primary key (operatorNodeID)
    );

    create table j3_operator_j3_contact (
        j3_operator_operatorNodeID varchar2(255 char) not null,
        contact_id number(19,0) not null
    );

    create table j3_operator_j3_key_info (
        j3_operator_operatorNodeID varchar2(255 char) not null,
        keyInfo_id number(19,0) not null
    );

    create table j3_overview_doc (
        id number(19,0) identity not null,
        overview_url varchar2(255 char),
        overview_url_use_type varchar2(255 char),
        entity_key varchar2(255 char),
        tomodel_instance_info_id number(19,0),
        primary key (id)
    );

    create table j3_overview_doc_descr (
        id number(19,0) identity not null,
        descr varchar2(1024 char) not null,
        lang_code varchar2(26 char),
        overview_doc_id number(19,0),
        primary key (id)
    );

    create table j3_person_name (
        id number(19,0) identity not null,
        lang_code varchar2(26 char),
        name varchar2(255 char) not null,
        contact_id number(19,0) not null,
        primary key (id)
    );

    create table j3_phone (
        id number(19,0) identity not null,
        phone_number varchar2(50 char) not null,
        use_type varchar2(255 char),
        contact_id number(19,0) not null,
        primary key (id)
    );

    create table j3_publisher (
        authorized_name varchar2(255 char) not null,
        email_address varchar2(255 char),
        is_admin number(1,0),
        is_enabled number(1,0),
        max_bindings_per_service number(10,0),
        max_businesses number(10,0),
        max_services_per_business number(10,0),
        max_tmodels number(10,0),
        publisher_name varchar2(255 char) not null,
        primary key (authorized_name)
    );

    create table j3_publisher_assertion (
        from_key varchar2(255 char) not null,
        to_key varchar2(255 char) not null,
        from_check varchar2(5 char) not null,
        key_name varchar2(255 char) not null,
        key_value varchar2(255 char) not null,
        modified timestamp not null,
        tmodel_key varchar2(255 char) not null,
        to_check varchar2(5 char) not null,
        primary key (from_key, to_key)
    );

    create table j3_reference (
        id number(19,0) identity not null,
        digest_method varchar2(255 char),
        digest_value blob,
        type varchar2(255 char),
        uri varchar2(255 char),
        xml_id varchar2(255 char),
        signed_info_key number(19,0) not null,
        primary key (id)
    );

    create table j3_service_category_bag (
        id number(19,0) not null,
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_service_descr (
        id number(19,0) identity not null,
        descr varchar2(1024 char) not null,
        lang_code varchar2(26 char),
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_service_name (
        id number(19,0) identity not null,
        lang_code varchar2(26 char),
        name varchar2(255 char) not null,
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_service_projection (
        business_key varchar2(255 char) not null,
        service_key varchar2(255 char) not null,
        primary key (business_key, service_key)
    );

    create table j3_signature (
        id number(19,0) identity not null,
        xml_id varchar2(255 char),
        binding_template_key varchar2(255 char),
        business_key varchar2(255 char),
        business_service_key varchar2(255 char),
        key_info number(19,0) not null,
        publisher_key varchar2(255 char),
        repl_config_key number(19,0),
        signature_value number(19,0) not null,
        signed_info number(19,0) not null,
        tmodel_key varchar2(255 char),
        primary key (id)
    );

    create table j3_signature_method (
        id number(19,0) identity not null,
        algorithm varchar2(255 char),
        primary key (id)
    );

    create table j3_signature_transform (
        id number(19,0) identity not null,
        transform varchar2(255 char),
        reference_key number(19,0) not null,
        primary key (id)
    );

    create table j3_signature_transform_data_v (
        id number(19,0) identity not null,
        content_bytes blob,
        content_type varchar2(255 char),
        signature_transform_key number(19,0) not null,
        primary key (id)
    );

    create table j3_signature_value (
        id number(19,0) identity not null,
        value_bytes blob,
        xml_id varchar2(255 char),
        primary key (id)
    );

    create table j3_signed_info (
        id number(19,0) identity not null,
        xml_id varchar2(255 char),
        canonicalization_method number(19,0) not null,
        signature_method number(19,0) not null,
        primary key (id)
    );

    create table j3_subscription (
        subscription_key varchar2(255 char) not null,
        authorized_name varchar2(255 char) not null,
        binding_key varchar2(255 char),
        brief number(1,0),
        create_date timestamp not null,
        expires_after timestamp,
        last_notified timestamp,
        max_entities number(10,0),
        notification_interval varchar2(255 char),
        subscription_filter clob not null,
        primary key (subscription_key)
    );

    create table j3_subscription_chunk_token (
        chunk_token varchar2(255 char) not null,
        data number(10,0) not null,
        end_point timestamp,
        expires_after timestamp not null,
        start_point timestamp,
        subscription_key varchar2(255 char) not null,
        primary key (chunk_token)
    );

    create table j3_subscription_match (
        id number(19,0) identity not null,
        entity_key varchar2(255 char) not null,
        subscription_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_temp_key (
        entity_key varchar2(255 char) not null,
        tx_id varchar2(255 char) not null,
        primary key (entity_key, tx_id)
    );

    create table j3_tmodel (
        deleted number(1,0),
        lang_code varchar2(26 char),
        name varchar2(255 char) not null,
        entity_key varchar2(255 char) not null,
        primary key (entity_key)
    );

    create table j3_tmodel_category_bag (
        id number(19,0) not null,
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_tmodel_descr (
        id number(19,0) identity not null,
        descr varchar2(255 char) not null,
        lang_code varchar2(26 char),
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_tmodel_identifier (
        id number(19,0) identity not null,
        key_name varchar2(255 char),
        key_value varchar2(255 char) not null,
        tmodel_key_ref varchar2(255 char),
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_tmodel_instance_info (
        id number(19,0) identity not null,
        instance_parms long,
        tmodel_key varchar2(255 char) not null,
        entity_key varchar2(255 char) not null,
        primary key (id)
    );

    create table j3_tmodel_instance_info_descr (
        id number(19,0) identity not null,
        descr varchar2(255 char) not null,
        lang_code varchar2(26 char),
        tmodel_instance_info_id number(19,0) not null,
        primary key (id)
    );

    create table j3_transfer_token (
        transfer_token varchar2(51 char) not null,
        expiration_date timestamp not null,
        primary key (transfer_token)
    );

    create table j3_transfer_token_keys (
        id number(19,0) identity not null,
        entity_key varchar2(255 char),
        transfer_token varchar2(51 char) not null,
        primary key (id)
    );

    create table j3_uddi_entity (
        entity_key varchar2(255 char) not null,
        authorized_name varchar2(255 char) not null,
        created timestamp,
        modified timestamp not null,
        modified_including_children timestamp,
        node_id varchar2(255 char) not null,
        primary key (entity_key)
    );

    create table j3_valuesets (
        j3_tmodelkey varchar2(255 char) not null,
        j3_validatorclass varchar2(255 char) not null,
        primary key (j3_tmodelkey)
    );

    alter table j3_binding_category_bag 
        add constraint UK_r10wn7w2t4xu1de24vl68t7ub  unique (entity_key);

    alter table j3_business_category_bag 
        add constraint UK_bgxyl0xta6skp7nlxptkgy6n8  unique (entity_key);

    alter table j3_chg_graph_j3_ctrl_msg 
        add constraint UK_2dyg6o0rf3lgdwsj2c3pbjda0  unique (controlMessage_id);

    alter table j3_chg_graph_j3_edge 
        add constraint UK_mg2rtotx2pv9w7negs7scbesu  unique (edge_id);

    alter table j3_chg_graph_j3_node 
        add constraint UK_jnuffd69ye652slmg7ccf1wmo  unique (node_name);

    alter table j3_chg_replconf_j3_operator 
        add constraint UK_4djhaan8x6tp8v9posumlwvkw  unique (operator_operatorNodeID);

    alter table j3_edge_j3_ctrl_msg 
        add constraint UK_5vplo6av7oysxl4evxd36ksag  unique (messages_id);

    alter table j3_edge_j3_node 
        add constraint UK_eunce9bhi8pd11t8jfgcf4ctk  unique (messageReceiverAlternate_name);

    alter table j3_node_j3_clerk 
        add constraint UK_9ogfmpx997ann3sshmafvjlf4  unique (clerks_clerk_name);

    alter table j3_operator_j3_contact 
        add constraint UK_8iykmjskt63ki2wxkhbbd1scc  unique (contact_id);

    alter table j3_operator_j3_key_info 
        add constraint UK_ihqhtf5jx4dovxmsa2i2trs63  unique (keyInfo_id);

    alter table j3_service_category_bag 
        add constraint UK_hedov3355eko1ybil9jikqtvs  unique (entity_key);

    alter table j3_tmodel_category_bag 
        add constraint UK_8179e9we7p6b8y59hcjgy82um  unique (entity_key);

    alter table j3_address 
        add constraint FK_qd8raf6okj97be6b3gp67lgc3 
        foreign key (address_id) 
        references j3_contact;

    alter table j3_address_line 
        add constraint FK_byxjih6njmsp2pr6cbo39hjnx 
        foreign key (address_id) 
        references j3_address;

    alter table j3_binding_category_bag 
        add constraint FK_r10wn7w2t4xu1de24vl68t7ub 
        foreign key (entity_key) 
        references j3_binding_template;

    alter table j3_binding_category_bag 
        add constraint FK_61g38g32ukfl3sotcmexmae2k 
        foreign key (id) 
        references j3_category_bag;

    alter table j3_binding_descr 
        add constraint FK_70p0vqn9q3injkm20akq0d7gt 
        foreign key (entity_key) 
        references j3_binding_template;

    alter table j3_binding_template 
        add constraint FK_cp1arnfnhc9cq4err12yca4af 
        foreign key (service_key) 
        references j3_business_service;

    alter table j3_binding_template 
        add constraint FK_lwstad1h14jvv2m0mdyisrmcb 
        foreign key (entity_key) 
        references j3_uddi_entity;

    alter table j3_business_category_bag 
        add constraint FK_bgxyl0xta6skp7nlxptkgy6n8 
        foreign key (entity_key) 
        references j3_business_entity;

    alter table j3_business_category_bag 
        add constraint FK_ljkcjr128qy8a9m46m2puye7e 
        foreign key (id) 
        references j3_category_bag;

    alter table j3_business_descr 
        add constraint FK_ofnoolxraf6wyx1jw9tfycbe8 
        foreign key (entity_key) 
        references j3_business_entity;

    alter table j3_business_entity 
        add constraint FK_s9uqkdfjfe64hnckgqo0k6dfd 
        foreign key (entity_key) 
        references j3_uddi_entity;

    alter table j3_business_identifier 
        add constraint FK_bgkkl1brl7jix5jlg0npdfg86 
        foreign key (entity_key) 
        references j3_business_entity;

    alter table j3_business_name 
        add constraint FK_fi4xrqxy6q9jep3a25gp1lgx2 
        foreign key (entity_key) 
        references j3_business_entity;

    alter table j3_business_service 
        add constraint FK_7d8lpk2odxkx26ehrnk8rqbsr 
        foreign key (business_key) 
        references j3_business_entity;

    alter table j3_business_service 
        add constraint FK_jiiao6dsx7ynviccllmgmjc9s 
        foreign key (entity_key) 
        references j3_uddi_entity;

    alter table j3_chg_graph_j3_ctrl_msg 
        add constraint FK_2dyg6o0rf3lgdwsj2c3pbjda0 
        foreign key (controlMessage_id) 
        references j3_ctrl_msg;

    alter table j3_chg_graph_j3_ctrl_msg 
        add constraint FK_r9v4pr5jxfq50570m1a1r4cw 
        foreign key (j3_chg_graph_j3_id) 
        references j3_chg_graph;

    alter table j3_chg_graph_j3_edge 
        add constraint FK_mg2rtotx2pv9w7negs7scbesu 
        foreign key (edge_id) 
        references j3_edge;

    alter table j3_chg_graph_j3_edge 
        add constraint FK_713y71170d150ndlrx59j29xg 
        foreign key (j3_chg_graph_j3_id) 
        references j3_chg_graph;

    alter table j3_chg_graph_j3_node 
        add constraint FK_jnuffd69ye652slmg7ccf1wmo 
        foreign key (node_name) 
        references j3_node;

    alter table j3_chg_graph_j3_node 
        add constraint FK_hxbf3itu8an3kvq4catefchic 
        foreign key (j3_chg_graph_j3_id) 
        references j3_chg_graph;

    alter table j3_chg_replconf 
        add constraint FK_ool5rlgi50nc9togx95jvlily 
        foreign key (communicationGraph_j3_id) 
        references j3_chg_graph;

    alter table j3_chg_replconf 
        add constraint FK_htwoygqw48nuvbfiflhcsomi9 
        foreign key (contact_id) 
        references j3_contact;

    alter table j3_chg_replconf_j3_operator 
        add constraint FK_4djhaan8x6tp8v9posumlwvkw 
        foreign key (operator_operatorNodeID) 
        references j3_operator;

    alter table j3_chg_replconf_j3_operator 
        add constraint FK_4hhs13h922uthhum8xox8w342 
        foreign key (j3_chg_replconf_serialnumb) 
        references j3_chg_replconf;

    alter table j3_clerk 
        add constraint FK_sk3w9bhpme85s3o089nehgshx 
        foreign key (nodeid) 
        references j3_node;

    alter table j3_client_subscriptioninfo 
        add constraint FK_moftokp0pbowwmoluuamtd562 
        foreign key (fromClerk_clerk_name) 
        references j3_clerk;

    alter table j3_client_subscriptioninfo 
        add constraint FK_ls4hb4jmh1c6inha38iiwttts 
        foreign key (toClerk_clerk_name) 
        references j3_clerk;

    alter table j3_contact 
        add constraint FK_mt3vy807xu7av2td47fdooc7g 
        foreign key (entity_key) 
        references j3_business_entity;

    alter table j3_contact_descr 
        add constraint FK_b28tn8iofwgygdlflhdgfitq4 
        foreign key (contact_id) 
        references j3_contact;

    alter table j3_discovery_url 
        add constraint FK_d1pcky40a9b3obg92je3hbl80 
        foreign key (entity_key) 
        references j3_business_entity;

    alter table j3_edge 
        add constraint FK_qh2r0537gtghmwgjt7b8a7m42 
        foreign key (communicationGraph_j3_id) 
        references j3_chg_graph;

    alter table j3_edge 
        add constraint FK_byfhhlgvkyo7l09n32x9d8iuq 
        foreign key (messageReceiver_name) 
        references j3_node;

    alter table j3_edge 
        add constraint FK_r8yufoid46ttbkoetd4m1hq58 
        foreign key (messageSender_name) 
        references j3_node;

    alter table j3_edge_j3_ctrl_msg 
        add constraint FK_5vplo6av7oysxl4evxd36ksag 
        foreign key (messages_id) 
        references j3_ctrl_msg;

    alter table j3_edge_j3_ctrl_msg 
        add constraint FK_anobhkkcwuhnnkhcg3da8cxt0 
        foreign key (j3_edge_id) 
        references j3_edge;

    alter table j3_edge_j3_node 
        add constraint FK_eunce9bhi8pd11t8jfgcf4ctk 
        foreign key (messageReceiverAlternate_name) 
        references j3_node;

    alter table j3_edge_j3_node 
        add constraint FK_1fim4udlkg2t311nw3qrmst2t 
        foreign key (j3_edge_id) 
        references j3_edge;

    alter table j3_email 
        add constraint FK_ciwp1kdxmoa4fyijdxr2jn6fr 
        foreign key (contact_id) 
        references j3_contact;

    alter table j3_instance_details_descr 
        add constraint FK_5gir1w0vmkif9oar4nwk8exjc 
        foreign key (tmodel_instance_info_id) 
        references j3_tmodel_instance_info;

    alter table j3_instance_details_doc_descr 
        add constraint FK_trh0j83fd32sl0sfwunhu9o5h 
        foreign key (tmodel_instance_info_id) 
        references j3_tmodel_instance_info;

    alter table j3_key_data_value 
        add constraint FK_myvfuyy1yqcpu2d9v4c3vvt5s 
        foreign key (key_data_value_key) 
        references j3_key_data_value;

    alter table j3_key_data_value 
        add constraint FK_clqudtgwnrxignjcvsi08c0ds 
        foreign key (key_info_key) 
        references j3_key_info;

    alter table j3_keyed_reference 
        add constraint FK_78lvb5ololdt1bvyexrlyvi72 
        foreign key (category_bag_id) 
        references j3_category_bag;

    alter table j3_keyed_reference 
        add constraint FK_i5fttoj0y2n0is3g2ovlw4gtx 
        foreign key (keyed_reference_group_id) 
        references j3_keyed_reference_group;

    alter table j3_keyed_reference_group 
        add constraint FK_rtdautrakny2e0j0vc088fvmj 
        foreign key (category_bag_id) 
        references j3_category_bag;

    alter table j3_node_j3_clerk 
        add constraint FK_9ogfmpx997ann3sshmafvjlf4 
        foreign key (clerks_clerk_name) 
        references j3_clerk;

    alter table j3_node_j3_clerk 
        add constraint FK_lr1rq9pwxjuj7xh6c2ti6065h 
        foreign key (j3_node_name) 
        references j3_node;

    alter table j3_object_type 
        add constraint FK_rkye9a5p332fj5bm6t00qf3a7 
        foreign key (signature_key) 
        references j3_signature;

    alter table j3_object_type_content 
        add constraint FK_6799ef7af7ympm2yvrnp6vr31 
        foreign key (object_type_key) 
        references j3_object_type;

    alter table j3_operator_j3_contact 
        add constraint FK_8iykmjskt63ki2wxkhbbd1scc 
        foreign key (contact_id) 
        references j3_contact;

    alter table j3_operator_j3_contact 
        add constraint FK_l417isp8r8252fir8yiae1wx7 
        foreign key (j3_operator_operatorNodeID) 
        references j3_operator;

    alter table j3_operator_j3_key_info 
        add constraint FK_ihqhtf5jx4dovxmsa2i2trs63 
        foreign key (keyInfo_id) 
        references j3_key_info;

    alter table j3_operator_j3_key_info 
        add constraint FK_e22gd3r3m2r6f2lbh5qma27uk 
        foreign key (j3_operator_operatorNodeID) 
        references j3_operator;

    alter table j3_overview_doc 
        add constraint FK_q237wf2ae5o7tdc7mesit7cct 
        foreign key (entity_key) 
        references j3_tmodel;

    alter table j3_overview_doc 
        add constraint FK_1512rufopvqs8awctdtog3mk0 
        foreign key (tomodel_instance_info_id) 
        references j3_tmodel_instance_info;

    alter table j3_overview_doc_descr 
        add constraint FK_s2q8dxrn82imvtwph0amomly9 
        foreign key (overview_doc_id) 
        references j3_overview_doc;

    alter table j3_person_name 
        add constraint FK_lnkioe94oeuj5uts2n34c1f6e 
        foreign key (contact_id) 
        references j3_contact;

    alter table j3_phone 
        add constraint FK_ly6urvfr2iyfbcm4ftrgxko9x 
        foreign key (contact_id) 
        references j3_contact;

    alter table j3_publisher_assertion 
        add constraint FK_o36uvif1qnjt6eglx5s8c9io8 
        foreign key (from_key) 
        references j3_business_entity;

    alter table j3_publisher_assertion 
        add constraint FK_62fd0ovo76od3w87l6cuwfgos 
        foreign key (to_key) 
        references j3_business_entity;

    alter table j3_reference 
        add constraint FK_gnlad58wbn5dlqpheda5shc9q 
        foreign key (signed_info_key) 
        references j3_signed_info;

    alter table j3_service_category_bag 
        add constraint FK_hedov3355eko1ybil9jikqtvs 
        foreign key (entity_key) 
        references j3_business_service;

    alter table j3_service_category_bag 
        add constraint FK_92wjn1cibqpk2m5kwop6adin0 
        foreign key (id) 
        references j3_category_bag;

    alter table j3_service_descr 
        add constraint FK_8lawb98ky4pdrdvoadtcvv7p4 
        foreign key (entity_key) 
        references j3_business_service;

    alter table j3_service_name 
        add constraint FK_o4e6yb9g0xit60pj4q2bql9ir 
        foreign key (entity_key) 
        references j3_business_service;

    alter table j3_service_projection 
        add constraint FK_36gqtshx1g3yofbl3dbl4shtm 
        foreign key (business_key) 
        references j3_business_entity;

    alter table j3_service_projection 
        add constraint FK_c9aeytjh7pr9v2sqhbagjnm30 
        foreign key (service_key) 
        references j3_business_service;

    alter table j3_signature 
        add constraint FK_q4p2knb6lhev3788kckxjojri 
        foreign key (binding_template_key) 
        references j3_binding_template;

    alter table j3_signature 
        add constraint FK_p9uwgwnplcq61e8fei1thfj6g 
        foreign key (business_key) 
        references j3_business_entity;

    alter table j3_signature 
        add constraint FK_s5q6dy9eohlecm5fstjlg8tub 
        foreign key (business_service_key) 
        references j3_business_service;

    alter table j3_signature 
        add constraint FK_of1j2ibtaea0vryctablkmms7 
        foreign key (key_info) 
        references j3_key_info;

    alter table j3_signature 
        add constraint FK_p8gr33qxbhvfoccoe9nrhhgjn 
        foreign key (publisher_key) 
        references j3_publisher;

    alter table j3_signature 
        add constraint FK_s5rlbwkcwbyorlhjksvihjxwn 
        foreign key (repl_config_key) 
        references j3_chg_replconf;

    alter table j3_signature 
        add constraint FK_hw0g2oe0hqmkn7kqujnse996y 
        foreign key (signature_value) 
        references j3_signature_value;

    alter table j3_signature 
        add constraint FK_hy19iauio7gsf70f6tkc6ejqr 
        foreign key (signed_info) 
        references j3_signed_info;

    alter table j3_signature 
        add constraint FK_siipjo5m8739yrwdy0wpsffs8 
        foreign key (tmodel_key) 
        references j3_tmodel;

    alter table j3_signature_transform 
        add constraint FK_5whk8shahqi14kyo949bcevim 
        foreign key (reference_key) 
        references j3_reference;

    alter table j3_signature_transform_data_v 
        add constraint FK_8vrysibdj6gp0vtbc75fsymvf 
        foreign key (signature_transform_key) 
        references j3_signature_transform;

    alter table j3_signed_info 
        add constraint FK_dvn6li7enlnn2o6d7pxhx6s3e 
        foreign key (canonicalization_method) 
        references j3_canonicalization_method;

    alter table j3_signed_info 
        add constraint FK_grlpg7upvxnyx6kcl8rw13tuc 
        foreign key (signature_method) 
        references j3_signature_method;

    alter table j3_subscription_match 
        add constraint FK_9yvyb154negpu1e2y2bxs4mk7 
        foreign key (subscription_key) 
        references j3_subscription;

    alter table j3_tmodel 
        add constraint FK_3xk8p0g4s7rn1uegoix9cpxih 
        foreign key (entity_key) 
        references j3_uddi_entity;

    alter table j3_tmodel_category_bag 
        add constraint FK_8179e9we7p6b8y59hcjgy82um 
        foreign key (entity_key) 
        references j3_tmodel;

    alter table j3_tmodel_category_bag 
        add constraint FK_5ntoksrysqt057v55h3l17kvq 
        foreign key (id) 
        references j3_category_bag;

    alter table j3_tmodel_descr 
        add constraint FK_pdm6qiiemjb5g2k7bloppky64 
        foreign key (entity_key) 
        references j3_tmodel;

    alter table j3_tmodel_identifier 
        add constraint FK_gpt1hm3piqn4fxmoicv263m7g 
        foreign key (entity_key) 
        references j3_tmodel;

    alter table j3_tmodel_instance_info 
        add constraint FK_dgpanh1of6721l7e1suggc4p7 
        foreign key (entity_key) 
        references j3_binding_template;

    alter table j3_tmodel_instance_info_descr 
        add constraint FK_epc0nb3g43lgq8gaa0j41k1mx 
        foreign key (tmodel_instance_info_id) 
        references j3_tmodel_instance_info;

    alter table j3_transfer_token_keys 
        add constraint FK_thx0vtrkl1m3d189a8muad7k7 
        foreign key (transfer_token) 
        references j3_transfer_token;

    create table JPAGEN_CFGGRPH (
         NAME varchar(255),
         VALUE int 
    );

    create table JPAGEN_GENERATORS (
         NAME varchar(255),
         VALUE int 
    );

    create table JPAGEN_REPLGEN (
         NAME varchar(255),
         VALUE int 
    );
