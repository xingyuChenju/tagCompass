datapath = 'Data';
addpath('Data');
ant_type = 's';
epc_col =1;
phase_col =2;
rssi_col =3;
data = load([datapath '\s.txt'])
figure
plot(data(:;rssi_col));