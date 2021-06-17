import axios, { AxiosResponse } from 'axios';
import { BugPayload } from '../models/BugPayload';

export const findAllBugs = async (): Promise<BugPayload[]> => {
    return axios.get('/bugs').then((response: AxiosResponse<BugPayload[]>) => response.data);
};
