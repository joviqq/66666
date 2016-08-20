drop trigger SEQ_TB_CON_ORD_ORDID_trigger;

create or replace trigger SEQ_TB_CON_ORD_ORDID_trigger
before insert
on TB_CON_ORD
for each row
begin
 SELECT 'DD00'||TO_CHAR(SYSDATE,'YYYYMMDD')||LPAD(SEQ_TB_CON_ORD_ORDID.NEXTVAL,6,'0') INTO :NEW.ORD_ID FROM DUAL;
end;
