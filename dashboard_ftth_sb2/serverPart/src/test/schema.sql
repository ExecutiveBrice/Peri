-- DONNEES DE TESTS DASHBOARD FTTH
-- Schema disponible dans le dossier xxx (TODO)

-- AJOUT DES OLT
INSERT INTO FTTH_OSP VALUES ('OLT0116-5-6', 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3');
INSERT INTO FTTH_OSP VALUES ('OLT0116-5-3', 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3');
INSERT INTO FTTH_OSP VALUES ('OLT0117-5-6', 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3');

-- AJOUT PM
-- AJOUT PM sous OLT0116-5-6
INSERT INTO FTTH_PM (ftth_pm_id, id_pm, ftth_osp_id, batch_id, last_gcr) VALUES (SEQ_FTTH_PM.nextval,'PM1', 'OLT0116-5-6','f3b585bf-ec8b-4f22-8d86-32964d4a61a3', null);
INSERT INTO FTTH_PM (ftth_pm_id, id_pm, ftth_osp_id, batch_id, last_gcr) VALUES (SEQ_FTTH_PM.nextval,'PM2', 'OLT0116-5-6','f3b585bf-ec8b-4f22-8d86-32964d4a61a3', null);
INSERT INTO FTTH_PM (ftth_pm_id, id_pm, ftth_osp_id, batch_id, last_gcr) VALUES (SEQ_FTTH_PM.nextval,'PM3', 'OLT0116-5-6','f3b585bf-ec8b-4f22-8d86-32964d4a61a3', null);
INSERT INTO FTTH_PM (ftth_pm_id, id_pm, ftth_osp_id, batch_id, last_gcr) VALUES (SEQ_FTTH_PM.nextval,'PM4', 'OLT0116-5-6','f3b585bf-ec8b-4f22-8d86-32964d4a61a3', null);

-- AJOUT PM sous OLT0117-5-6
INSERT INTO FTTH_PM (ftth_pm_id, id_pm, ftth_osp_id, batch_id, last_gcr) VALUES (SEQ_FTTH_PM.nextval,'PM3', 'OLT0117-5-6','f3b585bf-ec8b-4f22-8d86-32964d4a61a3', null);
INSERT INTO FTTH_PM (ftth_pm_id, id_pm, ftth_osp_id, batch_id, last_gcr) VALUES (SEQ_FTTH_PM.nextval,'PM4', 'OLT0117-5-6','f3b585bf-ec8b-4f22-8d86-32964d4a61a3', null);
INSERT INTO FTTH_PM (ftth_pm_id, id_pm, ftth_osp_id, batch_id, last_gcr) VALUES (SEQ_FTTH_PM.nextval,'PM5', 'OLT0117-5-6','f3b585bf-ec8b-4f22-8d86-32964d4a61a3', null);

-- AJOUT ONT
-- (ftth_ont_id, id_ont, is_active (DG?), batch_id, is_inact, inact_timestamp)
-- AJOUT ONTs pour PM1
-- ONT1
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 1, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 1, TO_DATE(SYSDATE - INTERVAL '10' DAY)); -- INACT depuis 10 jours
-- ONT2
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 2, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 1, CURRENT_DATE); -- INACT du jour
-- ONT3
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 3, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 1, CURRENT_DATE); -- INACT du jour
-- ONT4
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 4, 0, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 0, null); -- DG actif

-- AJOUT ONT pour PM2
-- ONT16
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 16, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 0, null); -- INACT cleared

-- AJOUT ONTs pour PM3
-- ONT32
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 32, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 1, CURRENT_DATE); -- INACT du jour
-- ONT33
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 33, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 1, CURRENT_DATE); -- INACT du jour
-- ONT34
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 34, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 1, CURRENT_DATE); -- INACT du jour
-- ONT1
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 1, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 0, null);

-- AJOUT ONTs pour PM4
-- ONT64
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 64, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 0, null);
-- ONT65
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 65, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 0, null);
-- ONT2
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 2, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 1, CURRENT_DATE); -- INACT du jour

-- AJOUT ONT pour PM5
-- ONT16
INSERT INTO FTTH_ONT (ftth_ont_id, id_ont, is_active, batch_id, is_inact, inact_timestamp) VALUES (SEQ_FTTH_ONT.nextval, 16, 1, 'f3b585bf-ec8b-4f22-8d86-32964d4a61a3', 1, TO_DATE(SYSDATE - INTERVAL '10' DAY)); -- INACT depuis 10 jours

-- AJOUT PMONT (table de liaison entre l'ONT et le PM)
-- AJOUT lien entre PM1 et ONT
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (1,1); -- PM1/ONT1
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (1,2); -- PM1/ONT2
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (1,3); -- PM1/ONT3
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (1,4); -- PM1/ONT4

-- AJOUT lien entre PM2 et ONT
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (2,5); -- PM2/ONT16

-- AJOUT lien entre PM3 et ONT
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (3,6); -- PM3/ONT32
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (3,7); -- PM3/ONT33
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (3,8); -- PM3/ONT34
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (5,9); -- PM3/ONT1 : Attention ONT1 sous PM3 different de ONT1 sous PM1

-- AJOUT lien entre PM4 et ONT
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (4,10); -- PM4/ONT64
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (4,11); -- PM4/ONT65
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (6,12); -- PM4/ONT2 : Attention ONT2 sous PM4 different de ONT2 sous PM1

-- AJOUT lien entre PM5 et ONT
--INSERT INTO FTTH_PMONT (ftth_pm_id, ftth_ont_id) VALUES (7,13); -- PM5/ONT16 : Attention ONT16 sous PM5 different de ONT16 sous PM2

-- AJOUT IG
-- IG minor PM sur PM1
--INSERT INTO FTTH_EVENTS (alarm_fullname, ftth_osp_id, sevrity, creation_timestamp, gcr, tt, clearance_timestamp, specific_problem) VALUES ('networkTrans .OSS11.TRANS.IDF pm "PM1"', 'OLT0116-5-6', 'minor', CURRENT_DATE, null, null, null, '{2}');
-- IG SNOW PM cleared sur PM2
--INSERT INTO FTTH_EVENTS (alarm_fullname, ftth_osp_id, sevrity, creation_timestamp, gcr, tt, clearance_timestamp, specific_problem) VALUES ('networkTrans .OSS11.TRANS.IDF pm "PM2"', 'OLT0116-5-6', 'major', TO_DATE(SYSDATE - INTERVAL '1' HOUR), null, null, CURRENT_DATE, '{1}');
-- IG SNOW PM sur PM3
--INSERT INTO FTTH_EVENTS (alarm_fullname, ftth_osp_id, sevrity, creation_timestamp, gcr, tt, clearance_timestamp, specific_problem) VALUES ('networkTrans .OSS11.TRANS.IDF pm "PM3"', 'OLT0116-5-6', 'major', CURRENT_DATE, null, null, null, '{1}');


