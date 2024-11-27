export interface Member {
    id: string;
    name: string;
    role: string;
    avatarUrl: string;
}

export interface Event {
    id: string;
    name: string;
    date: string;
    type: 'Trial' | 'Tournament';
}

export interface Club {
    id: string;
    name: string;
    description: string;
    establishedDate: string;
    logoUrl: string;
    teamType: 'Ultimate' | 'Disc Golf' | 'Freestyle' | 'Mixed';
    members: Member[];
    events: Event[];
}

