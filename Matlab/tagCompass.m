datapath = 'Data';
addpath('Data');
ant_type = 's';
epc_col =1;
phase_col =2;
rssi_col =3;
files = dir(datapath);
rssi_vals = [];
degrees =[];
for i = 3:length(files)
    filename = files(i).name;
    data = load(filename);
    rssi_val = mean(data(:,rssi_col));
    strs = split(filename,ant_type);
    degree = str2num(strs{1});
    rssi_vals = [rssi_vals;rssi_val];
    degrees = [degrees;degree];
end
figure
plot(degrees,rssi_vals,'o');