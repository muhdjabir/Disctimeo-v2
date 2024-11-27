export type EventType = 'Tournament' | 'Trial' | 'Training' | 'Social'

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

