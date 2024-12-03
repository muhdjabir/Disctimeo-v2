export enum EventType {
    Tournament = "Tournament",
    Trial = 'Trial',
    Training = 'Training',
    Social = 'Social',
}

interface Member {
    id: string
    name: string
    email: string
}

interface Club {
    id: string
    name: string
}

export interface Event {
    id: string
    name: string
    type: EventType
    description: string
    date: Date
    registrationDetails: string
    creatorTeam: Club | null
    creatorUser: Member | null
    participants: Member[]
}

