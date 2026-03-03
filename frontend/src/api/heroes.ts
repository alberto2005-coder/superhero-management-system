import { axiosInstance } from './axiosInstance';

export interface Heroe {
    id: number;
    nombre: string;
    universo: string;
    base: string;
    fechaDeAlta: string;
    equipo?: {
        id: number;
        nombre: string;
    };
}

export const getHeroes = async () => {
    const response = await axiosInstance.get<Heroe[]>('/heroes');
    return response.data;
};

export const getHeroeById = async (id: number) => {
    const response = await axiosInstance.get<Heroe>(`/heroes/${id}`);
    return response.data;
};

export const createHeroe = async (heroe: Omit<Heroe, 'id' | 'fechaDeAlta'>) => {
    const response = await axiosInstance.post<Heroe>('/heroes', heroe);
    return response.data;
};

export const updateHeroe = async (id: number, heroe: Partial<Heroe>) => {
    const response = await axiosInstance.put<Heroe>(`/heroes/${id}`, heroe);
    return response.data;
};

export const deleteHeroe = async (id: number) => {
    await axiosInstance.delete(`/heroes/${id}`);
};

export const searchHeroes = async (termino: string) => {
    const response = await axiosInstance.get<Heroe[]>('/heroes/buscar', {
        params: { termino }
    });
    return response.data;
};
