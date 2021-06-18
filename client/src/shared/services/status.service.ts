import axios, { AxiosResponse } from 'axios';
import { StatusPayload } from '../models/StatusPayload';

export const findAllStatuses = async (): Promise<StatusPayload[]> => {
    return axios.get('/status').then((response: AxiosResponse<StatusPayload[]>) => response.data);
};
