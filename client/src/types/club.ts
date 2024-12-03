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

export enum TeamType {
    Ultimate = "Ultimate",
    DiscGolf = "Disc Golf",
    Freestyle = "Freestyle",
    Mixed = "Mixed"
}

export interface Club {
    id: string;
    name: string;
    description: string;
    establishedDate: string;
    logoUrl: string;
    teamType: TeamType;
    members: Member[];
    events: Event[];
}

