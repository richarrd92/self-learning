DROP TABLE IF EXISTS 
qrtz_fired_triggers,
qrtz_paused_trigger_grps,
qrtz_scheduler_state,
qrtz_locks,
qrtz_simprop_triggers,
qrtz_blob_triggers,
qrtz_cron_triggers,
qrtz_simple_triggers,
qrtz_triggers,
qrtz_job_details;


SELECT column_name, data_type
FROM information_schema.columns
WHERE table_name = 'qrtz_job_details';

select * from qrtz_triggers;