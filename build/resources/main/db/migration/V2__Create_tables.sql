CREATE  TABLE organizations(
  organization NAME, inn INT,checking INT, PRIMARY KEY (organization)
);
CREATE TABLE waybill
(
  id           INT,
  date         DATE,
  organization NAME references organizations (organization),
  PRIMARY      KEY ( id)
);
CREATE  TABLE nomenclatures(
  name NAME,
  code INT,
   PRIMARY KEY  (name)
);
CREATE TABLE waybillPositions(
  id INT,
  price INT,
  nomenclature NAME references nomenclatures (name),
  quantity INT,
  waybillId INT references waybill (id),
    PRIMARY KEY (id)
);

