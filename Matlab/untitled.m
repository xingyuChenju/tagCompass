close all
clear all
path = 'ʵ������20200701\����\';
files = dir(path)
rssi_col = 3;
results = [];
for i = 3:length(files)
    filename = files(i).name;
    degree = str2num(filename(1:end-4));
    data = load([path filename]);
    if ~isempty(data) 
        rssi = mean(data(:,rssi_col));
        results = [results;[degree rssi]];
    end
end
figure
plot(results(:,1),results(:,2),'o')