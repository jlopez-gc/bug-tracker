INSERT INTO status VALUES (1, 'Pending');
INSERT INTO status VALUES (2, 'In Progress');
INSERT INTO status VALUES (3, 'Done');
INSERT INTO bugs(ID, NAME, DESCRIPTION, STATUS_ID, CREATED_AT, UPDATED_AT) VALUES (1, 'Create bug controller', 'Testing bug controller', 1, PARSEDATETIME('2021-06-16 22:00:00', 'YYYY-MM-DD HH:mm:ss'),PARSEDATETIME('2021-06-16 22:00:00', 'YYYY-MM-DD HH:mm:ss'));
INSERT INTO bugs(ID, NAME, DESCRIPTION, STATUS_ID, CREATED_AT, UPDATED_AT) VALUES (2, 'Create status controller', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas semper cursus eros, facilisis efficitur mauris. Nullam varius dolor a efficitur imperdiet. Fusce eget diam eget sapien elementum facilisis. Cras cursus tincidunt lobortis. Cras scelerisque volutpat lacus nec molestie. Donec sem risus, imperdiet vel lectus sed, mollis accumsan risus. Donec feugiat ipsum a nunc consectetur luctus. Integer ut turpis at quam ultricies feugiat. Donec pulvinar in sapien eget iaculis.', 2, PARSEDATETIME('2021-06-16 22:00:00', 'YYYY-MM-DD HH:mm:ss'),PARSEDATETIME('2021-06-16 22:00:00', 'YYYY-MM-DD HH:mm:ss'));